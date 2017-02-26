package org.techm.scheduler.respository;

import java.util.List;

import org.techm.scheduler.domain.Trigger;

/**
 * Declaring service methods for <class>Trigger</class> Repository implementations.
 * 
 * @author Himanshu
 *
 */
public interface TriggerRepository {

	Trigger createTrigger(Trigger trigger);

	Trigger updateTrigger(Trigger trigger);

	Trigger getTriggerById(String triggerId);

	List<Trigger> getAllTriggers();

	boolean deleteTrigger(String triggerId);

}
