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
import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class <class>TriggerListEntityWriter</class> is used to read the object
 * of type<class>List<Trigger></class> and convert it into json-array-body of
 * type "scheduler/trigger.mime". First it checks weather the given object of
 * type <class>List<Trigger></class> and then requested Mediatype. If both
 * match, It is appropriate writer to write List<Trigger> object into json-array
 * body.
 * 
 * @author Himanshu
 *
 */
@Provider
public class TriggerListEntityWriter implements MessageBodyWriter<List<Trigger>> {

	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(TriggerListEntityWriter.class);

	/**
	 * Check the requested media type and request object Type.
	 */
	@Override
	public boolean isWriteable(Class<?> typeClass, Type genericType, Annotation[] annotations, MediaType mediaType) {
		logger.info("Check the requested media type and request object Type");
		return (List.class.isAssignableFrom(typeClass)
				&& (((ParameterizedType) genericType).getActualTypeArguments()[0]).equals(Trigger.class)
				&& mediaType.toString().equals(SchedulerConstants.TRIGGER_MIME));
	}

	/**
	 * Unused.
	 */
	@Override
	public long getSize(List<Trigger> t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {

		return 0;
	}

	/**
	 * Convert the object of type <class>List<Trigger></class> into json-array
	 * formate of media type "scheduler/trigger.mime".
	 */
	@Override
	public void writeTo(List<Trigger> listTrigger, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		logger.info("Writing of array of json data into outputstream from the list of trigger object.");
		JsonWriter jsonWriter = Json.createWriter(entityStream);
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

		for (Trigger trigger : listTrigger) {
			JsonObjectBuilder triggerBuilder = Json.createObjectBuilder();

			// for Id property.
			if (trigger.getId() != null) {
				triggerBuilder.add(SchedulerConstants.ID_PROP, trigger.getId());
			}

			// for name property.
			if (trigger.getName() != null) {
				triggerBuilder.add(SchedulerConstants.NAME_PROP, trigger.getName());
			}

			// for schedulerType property.
			if (trigger.getSchedulerKey() != null) {
				JsonObjectBuilder triggerKeyBuilder = Json.createObjectBuilder();
				triggerKeyBuilder.add(SchedulerConstants.NAME_PROP, trigger.getSchedulerKey().getName());
				triggerKeyBuilder.add(SchedulerConstants.GROUP_PROP, trigger.getSchedulerKey().getGroup());
				triggerBuilder.add(SchedulerConstants.TRIGGER_KEY_PROP, triggerKeyBuilder);
			}

			// for trigger attributes.
			if (trigger.getSchedularAttributes() != null) {
				Map<String, String> mapAttributes = trigger.getSchedularAttributes();

				JsonArrayBuilder triggeAttributeBuilder = Json.createArrayBuilder();

				JsonObjectBuilder jsonAttribute = null;
				for (String schedulerKey : mapAttributes.keySet()) {
					jsonAttribute = Json.createObjectBuilder();
					jsonAttribute.add(SchedulerConstants.NAME_PROP, schedulerKey);
					jsonAttribute.add(SchedulerConstants.VALUE_PROP, mapAttributes.get(schedulerKey));
					triggeAttributeBuilder.add(jsonAttribute);
				}

				triggerBuilder.add(SchedulerConstants.SCHEDULER_ATTRIBUTES_PROP, triggeAttributeBuilder);
			}
			jsonArrayBuilder.add(triggerBuilder);
		}
		JsonArray jsonArray = jsonArrayBuilder.build();

		jsonWriter.writeArray(jsonArray);

		jsonWriter.close();
	}

}
