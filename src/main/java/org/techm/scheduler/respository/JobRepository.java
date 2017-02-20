package org.techm.scheduler.respository;

import java.util.List;

import org.techm.scheduler.domain.Job;

public interface JobRepository {

	Job createJob(Job Job);

	Job updateJob(Job job);

	Job getJobById(String jobId);

	List<Job> getAllJobs();

	boolean deleteJob(String jobId);

}
