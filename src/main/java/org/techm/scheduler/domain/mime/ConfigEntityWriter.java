package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Config;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class <class>ConfigEntityWriter</class> is used to read the object of
 * type<class>Config</class> and convert it into json-body of type
 * "scheduler/config.mime". First it checks weather the given object of type
 * <class>Config</class> and then requested Mediatype. If both match, It is
 * appropriate writer to write config object into json body.
 * 
 * @author Himanshu
 *
 */
@Provider
public class ConfigEntityWriter implements MessageBodyWriter<Config> {
	
	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(ConfigEntityWriter.class);

	/**
	 * Check the requested media type and request object Type.
	 */
	@Override
	public long getSize(Config config, Class<?> typeClass, Type type, Annotation[] arg3, MediaType mediaType) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Unused.
	 */
	@Override
	public boolean isWriteable(Class<?> typeClass, Type type, Annotation[] arg2, MediaType mediaType) {
		logger.info("Check weather the body content is of type \"scheduler/config.mime\" and type is Config.");
		return (typeClass == Config.class && mediaType.toString().equals(SchedulerConstants.CONFIG_MIME));
	}

	/**
	 * Convert the object of type <class>Config</class> into json-formated
	 * media type "scheduler/config.mime".
	 */
	@Override
	public void writeTo(Config config, Class<?> typeClass, Type type, Annotation[] arg3, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityWriter)
			throws IOException, WebApplicationException {
		logger.info("Write json-body into output stream from the object of config type.");

		JsonWriter jsonWriter = Json.createWriter(entityWriter);

		JsonObject jsonObject = createJsonFromObject(config);

		jsonWriter.writeObject(jsonObject);

		jsonWriter.close();

	}

	/**
	 * 
	 * @return
	 */
	public JsonObject createJsonFromObject(Config config) {
		logger.info("Converting config object into json equivalent.");
		
		JsonObjectBuilder configBuilder = Json.createObjectBuilder();

		if (config.getId() != null) {
			configBuilder.add(SchedulerConstants.ID_PROP, config.getId());
		}

		if (config.getCityId() != null) {
			configBuilder.add(SchedulerConstants.CITY_ID, config.getCityId());
		}

		if (config.getLocalityId() != null) {
			configBuilder.add(SchedulerConstants.LOCALITY_ID, config.getLocalityId());
		}

		if (config.getControllerId() != null) {
			configBuilder.add(SchedulerConstants.CONTROLLER_ID, config.getControllerId());
		}

		if (config.getJobId() != null) {
			configBuilder.add(SchedulerConstants.JOB_ID, config.getJobId());
		}

		if (config.getTriggerId() != null) {
			configBuilder.add(SchedulerConstants.TRIGGER_ID, config.getTriggerId());
		}

		if (config.getOnTime() != null) {
			configBuilder.add(SchedulerConstants.ON_TIME, config.getOnTime());
		}

		if (config.getOffTime() != null) {
			configBuilder.add(SchedulerConstants.OFF_TIME, config.getOffTime());
		}

		if (config.getDate() != null) {
			configBuilder.add(SchedulerConstants.DATE, config.getDate());
		}

		if (config.getDays() != null) {
			configBuilder.add(SchedulerConstants.DAYS, config.getDays());
		}

		if (config.getPhase() != null) {
			configBuilder.add(SchedulerConstants.PHASE, config.getPhase());
		}

		if (config.getDimType() != null) {
			configBuilder.add(SchedulerConstants.DIM_TYPE, config.getDimType());
		}

		configBuilder.add(SchedulerConstants.IS_DIM, config.isDim());

		if (config.getIntensity() != null) {
			configBuilder.add(SchedulerConstants.INTENSITY, config.getIntensity());
		}

		if (config.getConfigState() != null) {
			configBuilder.add(SchedulerConstants.CONFIG_STATE, config.getConfigState());
		}

		if (config.getConfigType() != null) {
			configBuilder.add(SchedulerConstants.CONFIG_TYPE, config.getConfigType());
		}

		if (config.getNextExecutionTime() != null) {
			configBuilder.add(SchedulerConstants.NEXT_EXECUTION, config.getNextExecutionTime());
		}

		if (config.getLastExecutionTime() != null) {
			configBuilder.add(SchedulerConstants.LAST_EXECUTION, config.getLastExecutionTime());
		}

		JsonObject jsonObject = configBuilder.build();
		return jsonObject;
	}

}
