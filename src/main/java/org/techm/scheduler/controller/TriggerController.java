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

@Path("job")
public class TriggerController {
	private TriggerService triggerService;

	@Inject
	public TriggerController(TriggerService triggerService) {
		this.triggerService = triggerService;
	}

	@POST
	@Consumes(SchedulerConstants.JOB_MIME)
	public Trigger createJob(Trigger trigger) {
		return triggerService.createTrigger(trigger);
	}

	@PUT
	@Consumes(SchedulerConstants.JOB_MIME)
	public Trigger updateJob(Trigger trigger) {
		return triggerService.updateTrigger(trigger);
	}

	@GET
	@Produces(SchedulerConstants.JOB_MIME)
	public List<Trigger> getJobAll() {
		List<Trigger> listOfTriggers = triggerService.getAllTrigger();
		return listOfTriggers;
	}

	@GET
	@Path("{triggerId}")
	@Produces(SchedulerConstants.JOB_MIME)
	public Trigger getJobById(@NotNull @PathParam("triggerId") String triggerId) {
		Trigger trigger = triggerService.getTriggerById(triggerId);
		return trigger;
	}

	@DELETE
	@Path("{triggerId}")
	public boolean deleteJob(@NotNull @PathParam("triggerId") String triggerId) {
		return triggerService.deleteTrigger(triggerId);
	}

}
