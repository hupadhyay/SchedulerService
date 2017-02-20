package org.techm.scheduler.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.service.TriggerService;
import org.techm.scheduler.utils.SchedulerConstants;

@Path("trigger")
public class TriggerController {
	private TriggerService triggerService;

	@Inject
	public TriggerController(TriggerService triggerService) {
		this.triggerService = triggerService;
	}

	@POST
	@Consumes(SchedulerConstants.TRIGGER_MIME)
	public Trigger createTrigger(Trigger trigger) {
		return triggerService.createTrigger(trigger);
	}

	@PUT
	@Consumes(SchedulerConstants.TRIGGER_MIME)
	public Trigger updateTrigger(Trigger trigger) {
		return triggerService.updateTrigger(trigger);
	}

	@GET
	@Produces(SchedulerConstants.TRIGGER_MIME)
	public List<Trigger> getAllTriggers() {
		List<Trigger> listOfTriggers = triggerService.getAllTriggers();
		return listOfTriggers;
	}

	@GET
	@Path("{triggerId}")
	@Produces(SchedulerConstants.TRIGGER_MIME)
	public Trigger getTriggerById(@NotNull @PathParam("triggerId") String triggerId) {
		Trigger trigger = triggerService.getTriggerById(triggerId);
		return trigger;
	}

	@DELETE
	@Path("{triggerId}")
	public boolean deleteTrigger(@NotNull @PathParam("triggerId") String triggerId) {
		return triggerService.deleteTrigger(triggerId);
	}

}
