package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import org.techm.scheduler.domain.Trigger;

@Provider
public class TriggerEntityWriter implements MessageBodyWriter<Trigger>{

	@Override
	public boolean isWriteable(Class<?> typeClass, Type type, Annotation[] annotations, MediaType mediaType) {
		return false;
	}

	@Override
	public long getSize(Trigger t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return 0;
	}

	@Override
	public void writeTo(Trigger t, Class<?> typeClass, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
	}

}
