package org.techm.scheduler.respository;

import java.util.List;

import org.techm.scheduler.domain.Trigger;

public interface TriggerRepository {

	Trigger createTrigger(Trigger trigger);

	Trigger updateTrigger(Trigger trigger);

	Trigger getTriggerById(String triggerId);

	List<Trigger> getAllTriggers();

	boolean deleteTrigger(String triggerId);

}
