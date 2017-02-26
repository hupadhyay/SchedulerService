package org.techm.scheduler.service;

import java.util.List;

import org.techm.scheduler.domain.Job;

/**
 * Declaring methods for <class>Job</class> Service layer implementations.
 * 
 * @author Himanshu
 *
 */
public interface JobService {
	
	Job createJob(Job job);
	
	Job updateJob(Job job);
	
	Job getJobById(String jobId);
	
	List<Job> getAllJobs();
	
	boolean deleteJob(String jobId);
}
