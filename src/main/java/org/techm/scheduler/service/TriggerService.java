package org.techm.scheduler.service;

import java.util.List;

import org.techm.scheduler.domain.Trigger;

public interface TriggerService {
	
	Trigger createTrigger(Trigger trigger);
	
	Trigger updateTrigger(Trigger trigger);
	
	Trigger getTriggerById(String triggerId);
	
	List<Trigger> getAllTrigger();
	
	boolean deleteTrigger(String triggerId);

}
