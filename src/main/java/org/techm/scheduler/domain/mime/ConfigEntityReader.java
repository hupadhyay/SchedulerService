package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Config;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class <class>ConfigEntityReader</class> is used to read the json-body of
 * <class>Config</class> and convert it into object of Config. First it will
 * check weather the given type of json body is "scheduler/config.mime" or not.
 * Once it confirm, It start reading of body content and prepare object of
 * config.
 * 
 * @author Himanshu
 *
 */
@Provider
public class ConfigEntityReader implements MessageBodyReader<Config> {

	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(ConfigEntityReader.class);

	/**
	 * Check weather the body content is of type "scheduler/config.mime" and
	 * desired type is <class>Config</class>.
	 */
	@Override
	public boolean isReadable(Class<?> typeClass, Type type, Annotation[] arg2, MediaType mediaType) {
		logger.info("Check weather the body content is of type \"scheduler/config.mime\" and type is Config.");
		return (type == Config.class && mediaType.toString().equals(SchedulerConstants.CONFIG_MIME));
	}

	/**
	 * Convert the given json in the <object>entityStream</object> into the
	 * object of <class>Config</class>.
	 */
	@Override
	public Config readFrom(Class<Config> typeClass, Type type, Annotation[] arg2, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		logger.info("Read json-body from inputstream and convert into Config type object.");

		Config config = null;

		JsonReader jsonReader = Json.createReader(entityStream);
		JsonObject jobObject = jsonReader.readObject();

		config = readFromJsonObject(jobObject);

		return config;
	}

	/**
	 * Reading of configuration object from jsonObject.
	 * 
	 * @param jobObject
	 * @return object of <class>Config</class>
	 */
	public Config readFromJsonObject(JsonObject jobObject) {

		Config config = new Config();

		if (jobObject.containsKey(SchedulerConstants.ID_PROP) && !(jobObject.isNull(SchedulerConstants.ID_PROP))) {
			String id = jobObject.getString(SchedulerConstants.ID_PROP);
			config.setId(id);
		}

		if (jobObject.containsKey(SchedulerConstants.CITY_ID) && !(jobObject.isNull(SchedulerConstants.CITY_ID))) {
			String cityId = jobObject.getString(SchedulerConstants.CITY_ID);
			config.setCityId(cityId);
		}

		if (jobObject.containsKey(SchedulerConstants.LOCALITY_ID)
				&& !(jobObject.isNull(SchedulerConstants.LOCALITY_ID))) {
			String localityId = jobObject.getString(SchedulerConstants.LOCALITY_ID);
			config.setLocalityId(localityId);
		}

		if (jobObject.containsKey(SchedulerConstants.CONTROLLER_ID)
				&& !(jobObject.isNull(SchedulerConstants.CONTROLLER_ID))) {
			String controllerId = jobObject.getString(SchedulerConstants.CONTROLLER_ID);
			config.setControllerId(controllerId);
		}

		if (jobObject.containsKey(SchedulerConstants.JOB_ID) && !(jobObject.isNull(SchedulerConstants.JOB_ID))) {
			String jobId = jobObject.getString(SchedulerConstants.JOB_ID);
			config.setJobId(jobId);
		}

		if (jobObject.containsKey(SchedulerConstants.TRIGGER_ID)
				&& !(jobObject.isNull(SchedulerConstants.TRIGGER_ID))) {
			String triggerId = jobObject.getString(SchedulerConstants.TRIGGER_ID);
			config.setTriggerId(triggerId);
		}

		if (jobObject.containsKey(SchedulerConstants.ON_TIME) && !(jobObject.isNull(SchedulerConstants.ON_TIME))) {
			String onTime = jobObject.getString(SchedulerConstants.ON_TIME);
			config.setOnTime(onTime);
		}

		if (jobObject.containsKey(SchedulerConstants.OFF_TIME) && !(jobObject.isNull(SchedulerConstants.OFF_TIME))) {
			String offTime = jobObject.getString(SchedulerConstants.OFF_TIME);
			config.setOffTime(offTime);
		}

		if (jobObject.containsKey(SchedulerConstants.DATE) && !(jobObject.isNull(SchedulerConstants.DATE))) {
			String date = jobObject.getString(SchedulerConstants.DATE);
			config.setDate(date);
		}

		if (jobObject.containsKey(SchedulerConstants.DAYS) && !(jobObject.isNull(SchedulerConstants.DAYS))) {
			String days = jobObject.getString(SchedulerConstants.DAYS);
			config.setDays(days);
		}

		if (jobObject.containsKey(SchedulerConstants.PHASE) && !(jobObject.isNull(SchedulerConstants.PHASE))) {
			String phase = jobObject.getString(SchedulerConstants.PHASE);
			config.setPhase(phase);
		}

		if (jobObject.containsKey(SchedulerConstants.DIM_TYPE) && !(jobObject.isNull(SchedulerConstants.DIM_TYPE))) {
			String dimType = jobObject.getString(SchedulerConstants.DIM_TYPE);
			config.setDimType(dimType);
		}

		if (jobObject.containsKey(SchedulerConstants.IS_DIM) && !(jobObject.isNull(SchedulerConstants.IS_DIM))) {
			boolean bool = jobObject.getBoolean(SchedulerConstants.IS_DIM);
			config.setDim(bool);
		}

		if (jobObject.containsKey(SchedulerConstants.INTENSITY) && !(jobObject.isNull(SchedulerConstants.INTENSITY))) {
			String isDim = jobObject.getString(SchedulerConstants.INTENSITY);
			config.setIntensity(isDim);
		}

		if (jobObject.containsKey(SchedulerConstants.CONFIG_STATE)
				&& !(jobObject.isNull(SchedulerConstants.CONFIG_STATE))) {
			String configState = jobObject.getString(SchedulerConstants.CONFIG_STATE);
			config.setConfigState(configState);
		}

		if (jobObject.containsKey(SchedulerConstants.CONFIG_TYPE)
				&& !(jobObject.isNull(SchedulerConstants.CONFIG_TYPE))) {
			String configType = jobObject.getString(SchedulerConstants.CONFIG_TYPE);
			config.setConfigType(configType);
		}

		if (jobObject.containsKey(SchedulerConstants.NEXT_EXECUTION)
				&& !(jobObject.isNull(SchedulerConstants.NEXT_EXECUTION))) {
			String nextExecution = jobObject.getString(SchedulerConstants.NEXT_EXECUTION);
			config.setNextExecutionTime(nextExecution);
		}

		if (jobObject.containsKey(SchedulerConstants.LAST_EXECUTION)
				&& !(jobObject.isNull(SchedulerConstants.LAST_EXECUTION))) {
			String lastExecution = jobObject.getString(SchedulerConstants.LAST_EXECUTION);
			config.setLastExecutionTime(lastExecution);
		}

		return config;
	}

}
