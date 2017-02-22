package org.techm.scheduler.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * 
 * @author Himanshu
 *
 */
@Provider
public class DaoExceptionMapper implements ExceptionMapper<DaoException> {

	@Override
	public Response toResponse(DaoException exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exception.getMessage())
				.type(MediaType.TEXT_PLAIN).build();
	}

}
