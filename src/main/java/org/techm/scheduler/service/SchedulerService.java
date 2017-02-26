package org.techm.scheduler.service;

import org.techm.scheduler.domain.Config;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.domain.Trigger;

/**
 * Declaring methods for <class>Scheduler</class> Service layer implementations.
 * 
 * @author Himanshu
 *
 */
public interface SchedulerService {
	
	boolean scheduleService(Job job, Trigger trigger, Config config, String urlAdd);
	
	boolean removeService(Trigger trigger); 
}
