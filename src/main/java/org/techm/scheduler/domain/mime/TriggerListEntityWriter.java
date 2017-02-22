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

import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.utils.SchedulerConstants;

@Provider
public class TriggerListEntityWriter implements MessageBodyWriter<List<Trigger>> {

	@Override
	public boolean isWriteable(Class<?> typeClass, Type genericType, Annotation[] annotations, MediaType mediaType) {

		return (List.class.isAssignableFrom(typeClass)
				&& (((ParameterizedType) genericType).getActualTypeArguments()[0]).equals(Trigger.class)
				&& mediaType.toString().equals(SchedulerConstants.TRIGGER_MIME));
	}

	@Override
	public long getSize(List<Trigger> t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType) {

		return 0;
	}

	@Override
	public void writeTo(List<Trigger> listTrigger, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

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
