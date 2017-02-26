package org.techm.scheduler.service;

import java.util.List;

import org.techm.scheduler.domain.Trigger;

/**
 * Declaring methods for <class>Trigger</class> Service layer implementations.
 * 
 * @author Himanshu
 *
 */
public interface TriggerService {
	
	Trigger createTrigger(Trigger trigger);
	
	Trigger updateTrigger(Trigger trigger);
	
	Trigger getTriggerById(String triggerId);
	
	List<Trigger> getAllTriggers();
	
	boolean deleteTrigger(String triggerId);

}
