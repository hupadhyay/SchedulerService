package org.techm.scheduler.service.impl;

import java.util.List;

import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.respository.TriggerRepository;
import org.techm.scheduler.service.TriggerService;

public class TriggerServiceImpl implements TriggerService {

	private TriggerRepository triggerRepository;

	@Override
	public Trigger createTrigger(Trigger trigger) {
		return triggerRepository.createTrigger(trigger);
	}

	@Override
	public Trigger updateTrigger(Trigger trigger) {
		return triggerRepository.updateTrigger(trigger);
	}

	@Override
	public Trigger getTriggerById(String triggerId) {
		return triggerRepository.getTriggerById(triggerId);
	}

	@Override
	public List<Trigger> getAllTrigger() {
		return triggerRepository.getAllTrigger();
	}

	@Override
	public boolean deleteTrigger(String triggerId) {
		return triggerRepository.deleteTrigger(triggerId);
	}

}
