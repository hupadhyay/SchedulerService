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

import org.techm.scheduler.domain.Job;
import org.techm.scheduler.service.JobService;
import org.techm.scheduler.utils.SchedulerConstants;

@Path("job")
public class JobController {
	
	private JobService jobService;
	
	@Inject
	public JobController(JobService jobService){
		this.jobService = jobService;
	}

	@POST
	@Consumes(SchedulerConstants.JOB_MIME)
	public Job createJob(Job job) {
		return jobService.createJob(job);
	}

	@PUT
	@Consumes(SchedulerConstants.JOB_MIME)
	public Job updateJob(Job job) {
		return jobService.updateJob(job);
	}

	@GET
	@Produces(SchedulerConstants.JOB_MIME)
	public List<Job> getJobAlls() {
		List<Job> listOfJobs = jobService.getAllJobs();
		return listOfJobs;
	}

	@GET
	@Path("{jobId}")
	@Produces(SchedulerConstants.JOB_MIME)
	public Job getJobById(@NotNull @PathParam("jobId") String jobId) {
		Job job = jobService.getJobById(jobId);
		return job;
	}

	@DELETE
	@Path("{jobId}")
	public boolean deleteJob(@NotNull @PathParam("jobId") String jobId) {
		return jobService.deleteJob(jobId);
	}
}
