package org.techm.scheduler.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.techm.scheduler.domain.Job;
import org.techm.scheduler.respository.JobRepository;
import org.techm.scheduler.service.JobService;

/**
 * Implementation of service interface <class>JobService</class>. Mainly
 * delegating call to repository layer.
 * 
 * @author Himanshu
 *
 */
public class JobServiceImpl implements JobService {

	/** Holds the instance of job repository service. */
	private JobRepository jobRepository;

	@Inject
	public JobServiceImpl(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

	@Override
	public Job createJob(Job Job) {
		return jobRepository.createJob(Job);
	}

	@Override
	public Job updateJob(Job job) {
		return jobRepository.updateJob(job);
	}

	@Override
	public Job getJobById(String jobId) {
		return jobRepository.getJobById(jobId);
	}

	@Override
	public List<Job> getAllJobs() {
		return jobRepository.getAllJobs();
	}

	@Override
	public boolean deleteJob(String jobId) {
		return jobRepository.deleteJob(jobId);
	}

}
