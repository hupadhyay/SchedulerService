package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.techm.scheduler.domain.Config;
import org.techm.scheduler.utils.SchedulerConstants;

@Provider
public class ConfigListEntityWriter implements MessageBodyWriter<List<Config>> {

	@Override
	public boolean isWriteable(Class<?> typeClass, Type genericType, Annotation[] annotations, MediaType mediaType) {

		return (List.class.isAssignableFrom(typeClass)
				&& (((ParameterizedType) genericType).getActualTypeArguments()[0]).equals(Config.class)
				&& mediaType.toString().equals(SchedulerConstants.CONFIG_MIME));
	}

	@Override
	public long getSize(List<Config> t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {

		return 0;
	}

	@Override
	public void writeTo(List<Config> listConfig, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

		JsonWriter jsonWriter = Json.createWriter(entityStream);
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Config config : listConfig) {
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

			jsonArrayBuilder.add(configBuilder);
		}
		JsonArray jsonArray = jsonArrayBuilder.build();

		jsonWriter.writeArray(jsonArray);

		jsonWriter.close();
	}

}
