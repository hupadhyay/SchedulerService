package org.techm.scheduler.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.techm.scheduler.respository.ConfigRepository;
import org.techm.scheduler.respository.JobRepository;
import org.techm.scheduler.respository.TriggerRepository;
import org.techm.scheduler.respository.impl.ConfigRepositoryImpl;
import org.techm.scheduler.respository.impl.JobRepositoryImpl;
import org.techm.scheduler.respository.impl.TriggerRepositoryImpl;
import org.techm.scheduler.service.ConfigService;
import org.techm.scheduler.service.JobService;
import org.techm.scheduler.service.SchedulerService;
import org.techm.scheduler.service.TriggerService;
import org.techm.scheduler.service.impl.ConfigServiceImpl;
import org.techm.scheduler.service.impl.JobServiceImpl;
import org.techm.scheduler.service.impl.SchedulerServiceImpl;
import org.techm.scheduler.service.impl.TriggerServiceImpl;

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
		register(RolesAllowedDynamicFeature.class);
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		System.out.println("Application called");

		// Binding of implementation classes with its interfaces.
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(JobServiceImpl.class).to(JobService.class);
				bind(JobRepositoryImpl.class).to(JobRepository.class);
				bind(TriggerServiceImpl.class).to(TriggerService.class);
				bind(TriggerRepositoryImpl.class).to(TriggerRepository.class);
				bind(ConfigServiceImpl.class).to(ConfigService.class);
				bind(ConfigRepositoryImpl.class).to(ConfigRepository.class);
				bind(SchedulerServiceImpl.class).to(SchedulerService.class);
			}
		});
	}
}
