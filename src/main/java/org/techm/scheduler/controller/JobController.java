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
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.service.JobService;
import org.techm.scheduler.utils.SchedulerConstants;

/**
 * This class is manage rest end-points for read/write operations of
 * <object>Job</object> objects.
 * 
 * @author Himanshu
 *
 */
@Path("job")
public class JobController {
	
	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(JobController.class);
	
	/** Instance of Job Service */
	private JobService jobService;
	
	/**
	 * Constructor Injection of <class>JobServiceImpl</class> to service
	 * instance of <class>JobService</class>.
	 * 
	 * @param jobService
	 */
	@Inject
	public JobController(JobService jobService){
		this.jobService = jobService;
	}

	/**
	 * POST Operation to create resource of type <class>Job</class>.
	 * 
	 * @param job
	 * @return newly created Job object.
	 */
	@POST
	@Consumes(SchedulerConstants.JOB_MIME)
	public Job createJob(Job job) {
		logger.info("Creating of job object. Object :" + job);
		return jobService.createJob(job);
	}

	/**
	 * PUT Operation to update resource of type <class>Job</class>.
	 * 
	 * @param trigger
	 * @return newly updated job object.
	 */
	@PUT
	@Consumes(SchedulerConstants.JOB_MIME)
	public Job updateJob(Job job) {
		logger.info("Updating of job object. Object :" + job);
		return jobService.updateJob(job);
	}

	/**
	 * Get All operation to get all the list of <object>Job</class>
	 * 
	 * @return list of job objects
	 */
	@GET
	@Produces(SchedulerConstants.JOB_MIME)
	public List<Job> getJobAlls() {
		logger.info("Retrieving all job objects.");
		List<Job> listOfJobs = jobService.getAllJobs();
		return listOfJobs;
	}

	/**
	 * Get the Job object based on given id.
	 * 
	 * @param jobId
	 * @return Job object.
	 */
	@GET
	@Path("{jobId}")
	@Produces(SchedulerConstants.JOB_MIME)
	public Job getJobById(@NotNull @PathParam("jobId") String jobId) {
		logger.info("Retrieving of job object based on id: " + jobId);
		Job job = jobService.getJobById(jobId);
		return job;
	}

	/**
	 * Delete the job object based on its Id.
	 * 
	 * @param jobId
	 * @return
	 */
	@DELETE
	@Path("{jobId}")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean deleteJob(@NotNull @PathParam("jobId") String jobId) {
		logger.info("Deleting of job object based on id: " + jobId);
		return jobService.deleteJob(jobId);
	}
}
