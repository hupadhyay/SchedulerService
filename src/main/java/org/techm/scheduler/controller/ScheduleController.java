package org.techm.scheduler.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.service.JobService;
import org.techm.scheduler.service.SchedulerService;
import org.techm.scheduler.service.TriggerService;

@Path("schedule")
public class ScheduleController {

	@Inject
	private JobService jobService;

	@Inject
	private TriggerService triggerService;

	@Inject
	private SchedulerService schedulerService;

	@POST
	@Path("create/job/{jobId}/trigger/{triggerId}/config/{configId}")
	public String createScheduler(@NotNull @PathParam("jobId") String jobId,
			@NotNull @PathParam("triggerId") String triggerId, @NotNull @PathParam("configId") String configId,
			@Context HttpServletRequest request) {
		Job job = jobService.getJobById(jobId);
		Trigger trigger = triggerService.getTriggerById(triggerId);
		String urlAdd = request.getRemoteAddr() + "/config";

		boolean bool = schedulerService.scheduleService(job, trigger, configId, urlAdd);

		if (bool) {
			return "Job with id: " + jobId + " is successfully scheduled against trigger with triggerId: " + triggerId;
		} else {
			return "Job could not scheduled.";
		}
	}

	@POST
	@Path("remove/trigger/{triggerId}")
	public String removeScheduler(@NotNull @PathParam("triggerId") String triggerId) {
		Trigger trigger = triggerService.getTriggerById(triggerId);

		boolean bool = schedulerService.removeService(trigger);

		if (bool) {
			return "Requested scheduling associated with trigger with id: " + triggerId + " has been cancelled.";
		} else {
			return "Scheduler could not be cancelled due to some internal error.";
		}
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("stop")
	public String stopScheduler() {
		String response = null;
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			if (scheduler.isStarted()) {
				scheduler.shutdown();
				response = "Scheduler is successfully shutdown.";
			} else {
				response = "Scheduler is already shutdown.";
			}
		} catch (SchedulerException exp) {
			exp.printStackTrace();
			response = "Scheduler could not be shutdown due to some internal error.";
			throw new org.techm.scheduler.exception.SchedulerException(response, exp.getCause());
		}
		return response;
	}

	/**
	 * 
	 * @return
	 */
	@GET
	@Path("start")
	public String startScheduler() {
		String response = null;
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			if (scheduler.isStarted()) {
				scheduler.start();
				response = "Scheduler is successfully started.";
			} else {
				response = "Scheduler is already running.";
			}
		} catch (SchedulerException exp) {
			exp.printStackTrace();
			response = "Scheduler could not be started due to some internal error.";
			throw new org.techm.scheduler.exception.SchedulerException(response, exp.getCause());
		}
		return response;
	}

	/**
	 * Clean all jobs from quartz repository.
	 * 
	 * @return
	 */
	@GET
	@Path("clean")
	public String cleanAllJob() {
		String response = null;
		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
			if (scheduler.isStarted()) {
				scheduler.shutdown();
				scheduler.clear();
				scheduler.startDelayed(2);
			} else {
				scheduler.clear();
			}
			response = "All jobs and triggers has been deleted from scheduler repository.";
		} catch (SchedulerException exp) {
			exp.printStackTrace();
			response = "Jobs and Triggers could not be clened due to some internal error.";
			throw new org.techm.scheduler.exception.SchedulerException(response, exp.getCause());
		}
		return response;
	}
}
