package org.techm.scheduler.domain.mime;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

import org.techm.scheduler.domain.Config;

public class ConfigEntityReader implements MessageBodyReader<Config>{

	@Override
	public boolean isReadable(Class<?> typeClass, Type type, Annotation[] arg2, MediaType mimeType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Config readFrom(Class<Config> typeClass, Type type, Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream arg5) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
