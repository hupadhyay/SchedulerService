package org.techm.scheduler.service;

import org.techm.scheduler.domain.Job;
import org.techm.scheduler.domain.Trigger;

/**
 * 
 * @author himanshu
 *
 */
public interface SchedulerService {
	
	boolean scheduleService(Job job, Trigger trigger, String configId, String urlAdd);
	
	boolean removeService(Trigger trigger); 
}
