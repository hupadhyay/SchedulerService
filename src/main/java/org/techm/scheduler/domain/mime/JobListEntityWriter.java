package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class <class>JobListEntityWriter</class> is used to read the object of
 * type<class>List<Job></class> and convert it into json-array-body of type
 * "scheduler/job.mime". First it checks weather the given object of type
 * <class>List<Job></class> and then requested Mediatype. If both match, It is
 * appropriate writer to write List<Job> object into json-array body.
 * 
 * @author Himanshu
 *
 */
@Provider
public class JobListEntityWriter implements MessageBodyWriter<List<Job>> {

	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(JobListEntityWriter.class);
	
	/**
	 * Check the requested media type and request object Type.
	 */
	@Override
	public boolean isWriteable(Class<?> typeClass, Type genericType, Annotation[] annotations, MediaType mediaType) {
		logger.info("Check for content type and target object type.");
		return (List.class.isAssignableFrom(typeClass)
				&& (((ParameterizedType) genericType).getActualTypeArguments()[0]).equals(Job.class)
				&& mediaType.toString().equals(SchedulerConstants.JOB_MIME));
	}

	/**
	 * Unused.
	 */
	@Override
	public long getSize(List<Job> t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {

		return 0;
	}

	/**
	 * Convert the object of type <class>List<Job></class> into json-array formate of media
	 * type "scheduler/job.mime".
	 */
	@Override
	public void writeTo(List<Job> listJob, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		logger.info("Writing of array of json data into outputStream from the list of objects of Job type.");

		JsonWriter jsonWriter = Json.createWriter(entityStream);
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Job job : listJob) {
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

			JsonArrayBuilder dataArrayBuilder = Json.createArrayBuilder();
			Map<String, String> mapDataAttrib = job.getJobDataAttributes();

			for (String strData : mapDataAttrib.keySet()) {
				JsonObjectBuilder dataAttribBuilder = Json.createObjectBuilder();
				dataAttribBuilder.add(SchedulerConstants.NAME_PROP, strData);
				dataAttribBuilder.add(SchedulerConstants.VALUE_PROP, mapDataAttrib.get(strData));
				dataArrayBuilder.add(dataAttribBuilder);
			}

			jobBuilder.add(SchedulerConstants.JOB_DATA_ATTRIBUTES_PROP, dataArrayBuilder);

			jsonArrayBuilder.add(jobBuilder);
		}
		JsonArray jsonArray = jsonArrayBuilder.build();

		jsonWriter.writeArray(jsonArray);

		jsonWriter.close();

	}

}
