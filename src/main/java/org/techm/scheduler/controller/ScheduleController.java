package org.techm.scheduler.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Config;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.service.ConfigService;
import org.techm.scheduler.service.JobService;
import org.techm.scheduler.service.SchedulerService;
import org.techm.scheduler.service.TriggerService;

/**
 * This class defines various end point to controll the Quartz job Scheduler
 * engine. It is used to schedule/unschedule jobs. It is also used to Start,
 * Stop and clean all jobs.
 * 
 * @author Himanshu
 *
 */
@Path("schedule")
public class ScheduleController {

	/** Holds the instance of Job Service */
	@Inject
	private JobService jobService;

	/** Holds the instance of Job Service */
	@Inject
	private TriggerService triggerService;

	/** Holds the instance of Job Service */
	@Inject
	private SchedulerService schedulerService;

	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(ScheduleController.class);

	/**
	 * Schedule the job against the trigger
	 * 
	 * @param jobId
	 * @param triggerId
	 * @param configId
	 * @param request
	 * @return true when the job is scheduled in quartz repository otherwise
	 *         false.
	 */
	@POST
	@Path("create/job/{jobId}/trigger/{triggerId}/config/{configId}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean createScheduler(@NotNull @PathParam("jobId") String jobId,
			@NotNull @PathParam("triggerId") String triggerId, @NotNull @PathParam("configId") String configId,
			@Context HttpServletRequest request) {
		logger.info("Scheduling Job against trigger in quartz repository.");
		Job job = jobService.getJobById(jobId);
		Trigger trigger = triggerService.getTriggerById(triggerId);
		
		int index = request.getRequestURL().indexOf("schedule");

		String urlAdd = request.getRequestURL().substring(0, index) + "config";

		boolean bool = schedulerService.scheduleService(job, trigger, configId, urlAdd);

		if (bool) {
			logger.info("Job with id: " + jobId + " is successfully scheduled against trigger with triggerId: "
					+ triggerId);
		} else {
			logger.info("Job could not scheduled.");
		}
		return bool;
	}

	/**
	 * To remove the schedule associated with trigger from the quartz schedule
	 * repository.
	 * 
	 * @param triggerId
	 * @return true when the schedule for trigger is removed from quartz
	 *         repository otherwise false.
	 */
	@POST
	@Path("remove/trigger/{triggerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean removeScheduler(@NotNull @PathParam("triggerId") String triggerId) {
		logger.info("Removing of scheduling from quartz repository.");
		Trigger trigger = triggerService.getTriggerById(triggerId);

		boolean bool = schedulerService.removeService(trigger);

		if (bool) {
			logger.info("Requested scheduling associated with trigger with id: " + triggerId + " has been cancelled.");
		} else {
			logger.info("Scheduler could not be cancelled due to some internal error.");
		}
		return bool;
	}

	/**
	 * This end point target to stop the scheduler engine.
	 * 
	 * @return message displaying weather scheduler is stop or not.
	 */
	@GET
	@Path("stop")
	public String stopScheduler() {
		logger.info("Stoping of quartz scheduler engine.");
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
			logger.error(response);
			throw new org.techm.scheduler.exception.SchedulerException(response, exp.getCause());
		}
		return response;
	}

	/**
	 * This end point target to start the scheduler end point.
	 * 
	 * @return message displaying weather scheduler is start or not.
	 */
	@GET
	@Path("start")
	public String startScheduler() {
		logger.info("Startring quartz scheduler engine.");
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
			logger.error(response);
			throw new org.techm.scheduler.exception.SchedulerException(response, exp.getCause());
		}
		return response;
	}

	/**
	 * Clean all jobs from quartz repository.
	 * 
	 * @return message displaying weather all jobs are clear
	 */
	@GET
	@Path("clean")
	public String cleanAllJob() {
		logger.info("Removing of all jobs from the quartz repository.");
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
			logger.error(response);
			throw new org.techm.scheduler.exception.SchedulerException(response, exp.getCause());
		}
		return response;
	}
}
