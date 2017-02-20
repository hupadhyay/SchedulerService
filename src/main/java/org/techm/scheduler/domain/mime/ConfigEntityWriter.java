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

import org.techm.scheduler.domain.Config;

@Provider
public class ConfigEntityWriter implements MessageBodyWriter<Config>{

	@Override
	public long getSize(Config config, Class<?> typeClass, Type type, Annotation[] arg3, MediaType mimeType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isWriteable(Class<?> typeClass, Type type, Annotation[] arg2, MediaType mimeType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void writeTo(Config config, Class<?> typeClass, Type type, Annotation[] arg3, MediaType mimeType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityWriter) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		
	}

}
