package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.ActionType;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.domain.JobAction;
import org.techm.scheduler.domain.SchedulerKey;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class <class>JobEntityReader</class> is used to read the json-body of
 * <class>Job</class> and convert it into object of Job. First it will check
 * weather the given type of json body is "scheduler/job.mime" or not. Once it
 * confirm, It start reading of body content and prepare object of Job.
 * 
 * @author Himanshu
 *
 */
@Provider
public class JobEntityReader implements MessageBodyReader<Job> {
	
	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(JobEntityReader.class);

	/**
	 * Check weather the body content is of type "scheduler/job.mime" and
	 * desired type is <class>Job</class>.
	 */
	@Override
	public boolean isReadable(Class<?> typeClass, Type type, Annotation[] annotations, MediaType mediaType) {
		
		logger.info("Check for content type and target object type.");
		return (typeClass == Job.class && mediaType.toString().equals(SchedulerConstants.JOB_MIME));
	}

	/**
	 * Convert the given json in the <object>entityStream</object> into the
	 * object of <class>Job</class>.
	 */
	@Override
	public Job readFrom(Class<Job> typeClass, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		logger.info("Reading of json data from input stream and converting it into object of Job type.");
		
		Job job = new Job();

		JsonReader reader = Json.createReader(entityStream);
		JsonObject jsonJob = reader.readObject();

		String id = jsonJob.getString(SchedulerConstants.ID_PROP);
		job.setId(id);
		String name = jsonJob.getString(SchedulerConstants.NAME_PROP);
		job.setName(name);

		if (!jsonJob.isNull(SchedulerConstants.JOB_KEY_PROP)) {
			SchedulerKey schedulerKey = new SchedulerKey();
			JsonObject jsonJobKey = jsonJob.getJsonObject(SchedulerConstants.JOB_KEY_PROP);

			String jobKeyName = jsonJobKey.getString(SchedulerConstants.NAME_PROP);
			schedulerKey.setName(jobKeyName);
			String jobKeyGroup = jsonJobKey.getString(SchedulerConstants.GROUP_PROP);
			schedulerKey.setGroup(jobKeyGroup);
			job.setSchedulerKey(schedulerKey);
		}

		JobAction jobAction = new JobAction();
		JsonObject jsonJobAction = jsonJob.getJsonObject(SchedulerConstants.JOB_ACTION_PROP);

		if (jsonJobAction.containsKey(SchedulerConstants.ACTION_TYPE_PROP)
				&& !(jsonJobAction.isNull(SchedulerConstants.ACTION_TYPE_PROP))) {
			String jobActionType = jsonJobAction.getString(SchedulerConstants.ACTION_TYPE_PROP);
			jobAction.setActionType(ActionType.safeValueOf(jobActionType));
		}

		if (jsonJobAction.containsKey(SchedulerConstants.DEVICE_ID_PROP)
				&& !(jsonJobAction.isNull(SchedulerConstants.DEVICE_ID_PROP))) {
			String jobDeviceId = jsonJobAction.getString(SchedulerConstants.DEVICE_ID_PROP);
			jobAction.setDeviceId(jobDeviceId);
		}

		if (jsonJobAction.containsKey(SchedulerConstants.COMMAND_ID_PROP)
				&& !(jsonJobAction.isNull(SchedulerConstants.COMMAND_ID_PROP))) {
			String jobCommandId = jsonJobAction.getString(SchedulerConstants.COMMAND_ID_PROP);
			jobAction.setCommandId(jobCommandId);
		}

		if (jsonJobAction.containsKey(SchedulerConstants.EVENT_ID_PROP)
				&& !(jsonJobAction.isNull(SchedulerConstants.EVENT_ID_PROP))) {
			String jobEventId = jsonJobAction.getString(SchedulerConstants.EVENT_ID_PROP);
			jobAction.setEventId(jobEventId);
		}

		if (jsonJobAction.containsKey(SchedulerConstants.MESSAGE_PROP)
				&& !(jsonJobAction.isNull(SchedulerConstants.MESSAGE_PROP))) {
			String jobMessage = jsonJobAction.getString(SchedulerConstants.MESSAGE_PROP);
			jobAction.setMessage(jobMessage);
		}

		if (jsonJobAction.containsKey(SchedulerConstants.GROUP_ID_PROP)
				&& !(jsonJobAction.isNull(SchedulerConstants.GROUP_ID_PROP))) {
			String jobGroupId = jsonJobAction.getString(SchedulerConstants.GROUP_ID_PROP);
			jobAction.setGroupId(jobGroupId);
		}

		if (jsonJobAction.containsKey(SchedulerConstants.STREAM_ID_PROP)
				&& !(jsonJobAction.isNull(SchedulerConstants.STREAM_ID_PROP))) {
			String jobStreamId = jsonJobAction.getString(SchedulerConstants.STREAM_ID_PROP);
			jobAction.setStreamId(jobStreamId);
		}

		if (jsonJobAction.containsKey(SchedulerConstants.RULE_TRIGGER_ID_PROP)
				&& !(jsonJobAction.isNull(SchedulerConstants.RULE_TRIGGER_ID_PROP))) {
			String jobRuleTrigger = jsonJobAction.getString(SchedulerConstants.RULE_TRIGGER_ID_PROP);
			jobAction.setRuleTriggerId(jobRuleTrigger);
		}

		job.setJobAction(jobAction);

		JsonArray jsonDataAttributes = jsonJob.getJsonArray(SchedulerConstants.JOB_DATA_ATTRIBUTES_PROP);

		JsonObject jsonDataAttrib = null;
		Map<String, String> mapDataAttributes = new HashMap<>();
		for (int i = 0; i < jsonDataAttributes.size(); i++) {
			jsonDataAttrib = jsonDataAttributes.getJsonObject(i);
			mapDataAttributes.put(jsonDataAttrib.getString(SchedulerConstants.NAME_PROP),
					jsonDataAttrib.getString(SchedulerConstants.VALUE_PROP));
		}

		job.setJobDataAttributes(mapDataAttributes);

		return job;
	}

}
