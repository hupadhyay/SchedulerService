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
import org.techm.scheduler.domain.SchedulerKey;
import org.techm.scheduler.domain.SchedulerType;
import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class <class>TriggerEntityReader</class> is used to read the json-body
 * of <class>Trigger</class> and convert it into object of Trigger. First it
 * checks weather the given type of json body is "scheduler/trigger.mime" or
 * not. Once it confirm, It start reading of body content and prepare object of
 * Trigger.
 * 
 * @author Himanshu
 *
 */
@Provider
public class TriggerEntityReader implements MessageBodyReader<Trigger> {

	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(TriggerEntityReader.class);

	/**
	 * Check weather the body content is of type "scheduler/trigger.mime" and
	 * desired type is <class>Trigger</class>.
	 */
	@Override
	public boolean isReadable(Class<?> typeClass, Type type, Annotation[] annotations, MediaType mediaType) {
		logger.info("Check for content type and target object type.");
		return (type == Trigger.class && mediaType.toString().equals(SchedulerConstants.TRIGGER_MIME));
	}

	/**
	 * Convert the given json in the <object>entityStream</object> into the
	 * object of <class>Trigger</class>.
	 */
	@Override
	public Trigger readFrom(Class<Trigger> typeClass, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		logger.info("Reading of json data from inputstream and converting it onto trigger object.");
		
		Trigger trigger = new Trigger();

		JsonReader reader = Json.createReader(entityStream);
		JsonObject jsonTrigger = reader.readObject();

		// for trigger id
		if (jsonTrigger.containsKey(SchedulerConstants.ID_PROP) && !(jsonTrigger.isNull(SchedulerConstants.ID_PROP))) {
			String id = jsonTrigger.getString(SchedulerConstants.ID_PROP);
			trigger.setId(id);
		}

		// for trigger name.
		if (jsonTrigger.containsKey(SchedulerConstants.NAME_PROP)
				&& !(jsonTrigger.isNull(SchedulerConstants.NAME_PROP))) {
			String name = jsonTrigger.getString(SchedulerConstants.NAME_PROP);
			trigger.setName(name);
		}

		// for scheduler type
		if (jsonTrigger.containsKey(SchedulerConstants.SCHEDULER_TYPE_PROP)
				&& !(jsonTrigger.isNull(SchedulerConstants.SCHEDULER_TYPE_PROP))) {
			String schedulerType = jsonTrigger.getString(SchedulerConstants.SCHEDULER_TYPE_PROP);
			trigger.setSchedulerType(SchedulerType.getSchedulerTypeEnum(schedulerType));
		}

		// for trigger key
		if (jsonTrigger.containsKey(SchedulerConstants.TRIGGER_KEY_PROP)
				&& !(jsonTrigger.isNull(SchedulerConstants.TRIGGER_KEY_PROP))) {
			SchedulerKey schedulerKey = new SchedulerKey();
			JsonObject triggerKey = (JsonObject) jsonTrigger.getJsonObject(SchedulerConstants.TRIGGER_KEY_PROP);

			String triggerKeyName = triggerKey.getString("name");
			schedulerKey.setName(triggerKeyName);
			String triggerKeyGroup = triggerKey.getString("group");
			schedulerKey.setGroup(triggerKeyGroup);

			trigger.setSchedulerKey(schedulerKey);
		}

		// for trigger Attributes.
		if (jsonTrigger.containsKey(SchedulerConstants.SCHEDULER_ATTRIBUTES_PROP)
				&& !(jsonTrigger.isNull(SchedulerConstants.SCHEDULER_ATTRIBUTES_PROP))) {
			JsonArray jsonSchedulerAttributes = jsonTrigger.getJsonArray(SchedulerConstants.SCHEDULER_ATTRIBUTES_PROP);

			JsonObject jsonDataAttrib = null;
			Map<String, String> mapDataAttributes = new HashMap<>();
			for (int i = 0; i < jsonSchedulerAttributes.size(); i++) {
				jsonDataAttrib = jsonSchedulerAttributes.getJsonObject(i);
				mapDataAttributes.put(jsonDataAttrib.getString(SchedulerConstants.NAME_PROP),
						jsonDataAttrib.getString(SchedulerConstants.VALUE_PROP));
			}

			trigger.setSchedularAttributes(mapDataAttributes);
		}

		return trigger;
	}

}
