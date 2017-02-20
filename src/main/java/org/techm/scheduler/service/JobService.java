package org.techm.scheduler.service;

import java.util.List;

import org.techm.scheduler.domain.Job;

public interface JobService {
	
	Job createJob(Job Job);
	
	Job updateJob(Job job);
	
	Job getJobById(String jobId);
	
	List<Job> getAllJob();
	
	boolean deleteJob(String jobId);
}
