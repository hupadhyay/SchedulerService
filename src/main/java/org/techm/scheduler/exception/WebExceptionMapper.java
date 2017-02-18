package org.techm.scheduler.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class WebExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException exception) {
		// TODO Auto-generated method stub
		return null;
	}

}
