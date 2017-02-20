package org.techm.scheduler.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.techm.scheduler.respository.JobRepository;
import org.techm.scheduler.respository.impl.JobRepositoryImpl;
import org.techm.scheduler.service.JobService;
import org.techm.scheduler.service.impl.JobServiceImpl;

/**
 * Execution point of web application.
 * 
 * @author Himanshu
 *
 */
@ApplicationPath("/rest")
public class WebApplication extends ResourceConfig {

	public WebApplication() {
		packages("org.techm.scheduler");
//		register(new LoggingFilter(AppLogger.getAppLogger(), true));
		register(RolesAllowedDynamicFeature.class);
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		System.out.println("Application called");
		
		register(new AbstractBinder() {
			
			@Override
			protected void configure() {
				bind(JobServiceImpl.class).to(JobService.class);
				bind(JobRepositoryImpl.class).to(JobRepository.class);
			}
		});
	}
}
