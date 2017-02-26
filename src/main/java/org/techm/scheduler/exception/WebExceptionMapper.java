package org.techm.scheduler.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This Exceptio Mapper will show some valuable and readble information to end
 * user in case of DAO WebApplicationException occurs.
 * 
 * @author Himanshu
 *
 */
@Provider
public class WebExceptionMapper implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException exception) {

		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage())
				.type(MediaType.TEXT_PLAIN).build();
	}

}
