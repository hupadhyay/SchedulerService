package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.techm.scheduler.domain.Job;
import org.techm.scheduler.utils.SchedulerConstants;

@Provider
public class JobEntityWriter implements MessageBodyWriter<Job> {

	@Override
	public boolean isWriteable(Class<?> className, Type type, Annotation[] annotations, MediaType mediaType) {
		return (type == Job.class && mediaType.toString().equals(SchedulerConstants.JOB_MIME));
	}

	@Override
	public long getSize(Job t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(Job job, Class<?> typeName, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		JsonWriter writer = Json.createWriter(entityStream);
		JsonObjectBuilder jobBuilder = Json.createObjectBuilder();

		jobBuilder.add(SchedulerConstants.ID_PROP, job.getId());
		jobBuilder.add(SchedulerConstants.NAME_PROP, job.getName());

		JsonObjectBuilder jobKeyBuilder = Json.createObjectBuilder();
		jobKeyBuilder.add(SchedulerConstants.NAME_PROP, job.getSchedulerKey().getName());
		jobKeyBuilder.add(SchedulerConstants.GROUP_PROP, job.getSchedulerKey().getGroup());

		jobBuilder.add(SchedulerConstants.JOB_KEY_PROP, jobKeyBuilder);

		JsonObjectBuilder jobActionBuilder = Json.createObjectBuilder();
		if (job.getJobAction().getActionType().value() != null) {
			jobActionBuilder.add(SchedulerConstants.ACTION_TYPE_PROP, job.getJobAction().getActionType().value());
		}
		if (job.getJobAction().getDeviceId() != null) {
			jobActionBuilder.add(SchedulerConstants.DEVICE_ID_PROP, job.getJobAction().getDeviceId());
		}
		if (job.getJobAction().getStreamId() != null) {
			jobActionBuilder.add(SchedulerConstants.STREAM_ID_PROP, job.getJobAction().getStreamId());
		}
		if (job.getJobAction().getCommandId() != null) {
			jobActionBuilder.add(SchedulerConstants.COMMAND_ID_PROP, job.getJobAction().getCommandId());
		}
		if (job.getJobAction().getEventId() != null) {
			jobActionBuilder.add(SchedulerConstants.EVENT_ID_PROP, job.getJobAction().getEventId());
		}
		if (job.getJobAction().getMessage() != null) {
			jobActionBuilder.add(SchedulerConstants.MESSAGE_PROP, job.getJobAction().getMessage());
		}
		if (job.getJobAction().getGroupId() != null) {
			jobActionBuilder.add(SchedulerConstants.GROUP_ID_PROP, job.getJobAction().getGroupId());
		}
		if (job.getJobAction().getRuleTriggerId() != null) {
			jobActionBuilder.add(SchedulerConstants.RULE_TRIGGER_ID_PROP, job.getJobAction().getRuleTriggerId());
		}

		jobBuilder.add(SchedulerConstants.JOB_ACTION_PROP, jobActionBuilder);

		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		Map<String, String> mapDataAttrib = job.getJobDataAttributes();

		for (String strData : mapDataAttrib.keySet()) {
			JsonObjectBuilder dataAttribBuilder = Json.createObjectBuilder();
			dataAttribBuilder.add(SchedulerConstants.NAME_PROP, strData);
			dataAttribBuilder.add(SchedulerConstants.VALUE_PROP, mapDataAttrib.get(strData));
			jsonArrayBuilder.add(dataAttribBuilder);
		}

		jobBuilder.add(SchedulerConstants.JOB_DATA_ATTRIBUTES_PROP, jsonArrayBuilder);

		JsonObject jobJson = jobBuilder.build();

		writer.write(jobJson);
		writer.close();
	}

}
