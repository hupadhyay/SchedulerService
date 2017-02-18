package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import org.techm.scheduler.domain.Job;

@Provider
public class JobEntityReader implements MessageBodyReader<Job>{

	@Override
	public boolean isReadable(Class<?> typeClass, Type type, Annotation[] annotations, MediaType mediaType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Job readFrom(Class<Job> typeClass, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
