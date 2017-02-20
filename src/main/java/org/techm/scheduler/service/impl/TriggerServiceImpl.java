package org.techm.scheduler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.respository.TriggerRepository;
import org.techm.scheduler.service.TriggerService;

public class TriggerServiceImpl implements TriggerService {

	private TriggerRepository triggerRepository;
	
	@Inject
	public TriggerServiceImpl(TriggerRepository triggerRepository){
		this.triggerRepository = triggerRepository;
	}

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
	public List<Trigger> getAllTriggers() {
		return triggerRepository.getAllTriggers();
	}

	@Override
	public boolean deleteTrigger(String triggerId) {
		return triggerRepository.deleteTrigger(triggerId);
	}

}
