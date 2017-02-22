package org.techm.scheduler.utils;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.internal.util.Base64;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Config;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.domain.JobAction;
import org.techm.scheduler.exception.SchedulerException;

public class QuartzJob implements org.quartz.Job {

	String reqeustURL = null;

	String[] weekdays = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJob.class);

	/**
	 * When the time is expire, Quartz scheduler, will execute this method.
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
		Job job = (Job) jobDataMap.get("job");
		String configId = (String) jobDataMap.get("configId");
		reqeustURL = (String) jobDataMap.get("url");
		JobAction jobAction = job.getJobAction();
		Config config = getConfigObject(configId);
		LOGGER.info("#############: Message: " + jobAction.getMessage());

		String jobId = job.getId();
		int ind = job.getId().lastIndexOf("_");
		String prefix = jobId.substring(0, ind);

		// for send command only
		String deviceId = jobAction.getDeviceId();
		String commandId = jobAction.getCommandId();
		String message = jobAction.getMessage();
		String messageId = UUID.randomUUID().toString();

		JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
		jsonBuilder.add("messageId", messageId);
		jsonBuilder.add("creation", System.currentTimeMillis());
		jsonBuilder.add("deviceId", deviceId);
		jsonBuilder.add("commandId", commandId);
		jsonBuilder.add("message", encodeMessage(message.getBytes()));
		jsonBuilder.add("encodingType", "BASE64");

		JsonObject jsonObject = jsonBuilder.build();

		try {
			String acessToken = "Bearer " + getAuthToken();
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("https://apistg.np.covapp.io/composer/v1/composer/command");

			Response response = webTarget.request("application/vnd.com.covisint.platform.messaging.sendCommand.v1+json")
					.header("Content-Type", "application/vnd.com.covisint.platform.messaging.sendCommand.v1+json")
					.header("Authorization", acessToken).post(Entity.json(jsonObject.toString()));

			// remove the Older Job from Next Execution expression
			String removedNextExecutionTime = removeOldNextExecutionTime(config, prefix);
			if (response.getStatus() == 200) {
				LOGGER.info("Command has been send successfully!");

				String strResponse = response.readEntity(String.class);
				LOGGER.info("Platform Response: " + strResponse);

				// update the LastExecution time
				updateLastExecutionTime(config, prefix, removedNextExecutionTime);

			} else {
				LOGGER.info("Error Response Code: " + response.getStatus());
				LOGGER.info("Command could not send to plateform due to some error.!");

				if (config.isDim()) {
					config.setLastExecutionTime(prefix + "::failed");
				} else {
					if (config.getLastExecutionTime() != null) {
						config.setLastExecutionTime(config.getLastExecutionTime() + "|" + prefix + "::failed");
					} else {
						config.setLastExecutionTime(prefix + "::failed");
					}
				}
			}
			if (config != null) {

				Calendar cal = Calendar.getInstance();
				cal.setTime(new Date());

				// Code to Set the Initial Job time to Calender, so that
				// same will be move forward.
				String[] timeArray = removedNextExecutionTime.split("::")[2].split(":");
				cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]));
				cal.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]));
				cal.set(Calendar.SECOND, Integer.parseInt(timeArray[2]));

				if (config.getConfigType().equals("weekly")) {
					int index = cal.get(Calendar.DAY_OF_WEEK);
					String selectedDays = config.getDays();
					String[] arrDays = selectedDays.split(",");
					int duration = getDaysGap(index, arrDays);
					cal.add(Calendar.DATE, duration);
					if (config.getConfigState().equalsIgnoreCase("sunrise")) {
						String strTime = getSunriseSunsetTime(cal.getTime());
						config.setNextExecutionTime(config.getNextExecutionTime() + "|" + prefix + "::"
								+ getFormatedDate1(cal.getTime()) + "::" + strTime);
					} else {
						if (config.isDim()) {
							config.setNextExecutionTime(prefix + "::" + getFormatedDate(cal.getTime()));
						} else {
							config.setNextExecutionTime(config.getNextExecutionTime() + "|" + prefix + "::"
									+ getFormatedDate(cal.getTime()));
						}
					}
				} else if (config.getConfigType().equals("daily")) {
					cal.add(Calendar.DATE, 1);
					if (config.getConfigState().equalsIgnoreCase("sunrise")) {
						String strTime = getSunriseSunsetTime(cal.getTime());
						config.setNextExecutionTime(config.getNextExecutionTime() + "|" + prefix + "::"
								+ getFormatedDate1(cal.getTime()) + "::" + strTime);
					} else {
						if (config.isDim()) {
							config.setNextExecutionTime(prefix + "::" + getFormatedDate(cal.getTime()));
						} else {
							config.setNextExecutionTime(config.getNextExecutionTime() + "|" + prefix + "::"
									+ getFormatedDate(cal.getTime()));
						}
					}
				} else {
					if (config.isDim()) {
						config.setNextExecutionTime("expired");
					} else {
						config.setNextExecutionTime(config.getNextExecutionTime() + "|" + " expired");
					}
				}
				LOGGER.info("Next execution date: " + config.getNextExecutionTime());
				updateConfigObject(config);
			}
		} catch (Exception exp) {
			LOGGER.info("Exception occurs due to some internal error. Message: " + exp.getMessage());
			throw new SchedulerException("Could not execute scheduled job due to some internal error", exp.getCause());
		}
	}

	/**
	 * Get the time of sunrise and sunset from
	 * <site>http://api.sunrise-sunset.org</site>.
	 * 
	 * @return
	 */
	private String getSunriseSunsetTime(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strdate = dateFormat.format(date);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("http://api.sunrise-sunset.org/json?lat=36.7201600&lng=-4.4203400&date="
					+ strdate + "&formatted=0");

			Response response = webTarget.request(MediaType.APPLICATION_JSON).get();

			if (response.getStatus() == 200) {
				JsonReader jsonReader = Json.createReader(new StringReader(response.readEntity(String.class)));
				JsonObject jsonResponse = jsonReader.readObject();
				jsonReader.close();

				if (jsonResponse.getString("status").equals("OK")) {
					JsonObject jsonResult = jsonResponse.getJsonObject("results");
					String result = null;
					if (hourOfDay < 12)
						result = jsonResult.getString("sunrise");
					else
						result = jsonResult.getString("sunset");
					result = result.substring((result.indexOf("T") + 1), result.indexOf("+"));
					return result;
				} else {
					return null;
				}
			}
			return null;
		} catch (Exception exp) {
			LOGGER.error("Could not fetch data from site sunrise and sunset. Message:" + exp.getMessage());
			throw new SchedulerException("Unable to get Sunrise/Sunset for Date: " + date);
		}

	}

	/**
	 * Remove the old next execution time.
	 * 
	 * @param config
	 * @param prefix
	 * @return
	 */
	private String removeOldNextExecutionTime(Config config, String prefix) {
		// remove older NextExecutionTime
		String nextExecution = config.getNextExecutionTime();
		String udatedExecutionTime = null;
		String removedExecutionTime = null;

		if (config.isDim()) {
			return nextExecution;
		}

		String arrNext[] = nextExecution.split("\\|");
		if (arrNext[0].startsWith(prefix)) {
			udatedExecutionTime = arrNext[1];
			removedExecutionTime = arrNext[0];
		} else {
			udatedExecutionTime = arrNext[0];
			removedExecutionTime = arrNext[1];
		}
		config.setNextExecutionTime(udatedExecutionTime);

		// remove older LastExecutionTime
		String nextLastExecution = config.getLastExecutionTime();
		String updatedLastExecutionTime = null;

		if (nextLastExecution != null) {
			arrNext = nextLastExecution.split("\\|");
			if (arrNext.length > 1) {
				if (arrNext[0].startsWith(prefix)) {
					updatedLastExecutionTime = arrNext[1];
				} else {
					updatedLastExecutionTime = arrNext[0];
				}
				config.setLastExecutionTime(updatedLastExecutionTime);
			}
		}

		return removedExecutionTime;

	}

	/**
	 * 
	 * @param config
	 * @param prefix
	 * @param removedNextExecutionTime
	 */
	private void updateLastExecutionTime(Config config, String prefix, String removedNextExecutionTime) {
		if (config.isDim()) {
			config.setLastExecutionTime(removedNextExecutionTime);
		} else {
			String lastExecution = config.getLastExecutionTime();

			if (lastExecution == null) {
				config.setLastExecutionTime(removedNextExecutionTime);
			} else {
				config.setLastExecutionTime(lastExecution + "|" + removedNextExecutionTime);
			}
		}
	}

	/**
	 * to update config object.
	 * 
	 * @param config
	 */
	private void updateConfigObject(Config config) {
		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(reqeustURL);

			Response response = webTarget.request(SchedulerConstants.CONFIG_MIME)
					.header("Content-Type", SchedulerConstants.CONFIG_MIME)
					.put(Entity.entity(config, SchedulerConstants.CONFIG_MIME));

			if (response.getStatus() == 200) {
				LOGGER.info("Config object is udpated successfully");
			}

		} catch (Exception exp) {
			LOGGER.error("Could not update config object. Error Message:" + exp.getMessage());
			throw new SchedulerException("Unable to update Config object", exp.getCause());
		}
	}

	/**
	 * Config Object by id.
	 * 
	 * @param configId
	 * @return
	 */
	private Config getConfigObject(String configId) {
		Config config = null;
		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target(reqeustURL + configId);

			Response response = webTarget.request(SchedulerConstants.CONFIG_MIME).get();

			if (response.getStatus() == 200) {
				config = response.readEntity(Config.class);
			} else {
				LOGGER.error("Not able to get config object from id.");
				// throw exception.
			}
		} catch (Exception exp) {
			LOGGER.error("Could not retrive config object from id. Error Message:" + exp.getMessage());
			throw new SchedulerException("Unable to get Config object", exp.getCause());
		}
		return config;
	}

	/**
	 * Get gap between days.
	 * 
	 * @param index
	 * @param arrDays
	 * @return
	 */
	private int getDaysGap(int index, String[] arrDays) {
		int gapDays = 0;
		int[] dayIndices = new int[arrDays.length];
		int j = 0;
		int selIndex = -1;

		for (int i = 0; i < weekdays.length; i++) {
			if (weekdays[i].equalsIgnoreCase(arrDays[j])) {
				dayIndices[j] = (i + 1);
				if ((i + 1) == index) {
					selIndex = j;
				}
				j++;
				if (j == arrDays.length) {
					break;
				}
			}
		}
		if (index == dayIndices[dayIndices.length - 1]) {
			gapDays = weekdays.length - index + dayIndices[0];
		} else {
			gapDays = dayIndices[(selIndex + 1)] - dayIndices[selIndex];
		}

		return gapDays;
	}

	/**
	 * Return formated date ...
	 * 
	 * @param date
	 * @return
	 */
	private String getFormatedDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy'::'HH:mm:ss");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	/**
	 * Return formated date ...
	 * 
	 * @param date
	 * @return
	 */
	private String getFormatedDate1(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	/**
	 * Encode the message with base64 to byte array.
	 * 
	 * @param base64Msg
	 *            Message in Base64.
	 * @return the encoded String.
	 */
	private static String encodeMessage(byte[] encryptedSendEventMessage) {
		return new String(Base64.encode(encryptedSendEventMessage));
	}

	/**
	 * To get token.
	 * 
	 * @return token
	 * @throws ParseException
	 * @throws UniformInterfaceException
	 * @throws ClientHandlerException
	 */
	public static String getAuthToken() throws Exception {
		String token = null;
		try {
			Client client = ClientBuilder.newClient();
			WebTarget webTarget = client.target("https://apistg.np.covapp.io/oauth/v3/token");

			MultivaluedMap<String, String> map = new MultivaluedHashMap<String, String>();
			map.add("grant_type", "client_credentials");
			map.add("scope", "all");

			Response response = webTarget.request(MediaType.APPLICATION_JSON)
					.header("Authorization",
							"Basic UERieEpwQUpyS2NIUU5wU1NGcmZLd0VzNVY5d2lvWnc6ZUxpNEV4aFgyT0F1R3VMdw==")
					.header("Content-Type", "application/x-www-form-urlencoded").post(Entity.form(map));

			if (response.getStatus() == 200) {
				token = fetchToken(response.readEntity(String.class));
				System.out.println(token);
			} else {
				throw new Exception(response.getStatus() + ", Details: " + response.readEntity(String.class));
			}
		} catch (Exception exp) {
			throw new SchedulerException("Unable to get token. Message: " + exp.getMessage());
		}
		return token;
	}

	/**
	 * 
	 * @param entity
	 * @return5
	 */
	private static String fetchToken(String entity) {
		JsonReader jsonReader = Json.createReader(new StringReader(entity));
		JsonObject object = jsonReader.readObject();
		jsonReader.close();
		String token = object.getString("access_token");
		return token;
	}

	/**
	 * For testing of quartz Job.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// QuartzJob job = new QuartzJob();
		// int gap = job.getDaysGap(3, new String[] { "TUE", "THU", "SAT" });
		// System.out.println(gap);

		System.out.println(QuartzJob.getAuthToken());
	}
}