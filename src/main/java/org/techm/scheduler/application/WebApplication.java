package org.techm.scheduler.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.techm.scheduler.utils.AppLogger;

/**
 * Execution point of web application.
 * 
 * @author Himanshu
 *
 */
@ApplicationPath("/rest")
public class WebApplication extends ResourceConfig {

	public WebApplication() {
		packages("org.techm.scheduler.*");
		register(new LoggingFilter(AppLogger.getAppLogger(), true));
		register(RolesAllowedDynamicFeature.class);
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
	}
}
