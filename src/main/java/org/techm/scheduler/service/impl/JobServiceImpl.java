package org.techm.scheduler.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.techm.scheduler.domain.Job;
import org.techm.scheduler.respository.JobRepository;
import org.techm.scheduler.service.JobService;

public class JobServiceImpl implements JobService{
	
	private JobRepository jobRepository;
	
	@Inject
	public JobServiceImpl(JobRepository jobRepository){
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
	public List<Job> getAllJob() {
		return jobRepository.getAllJob();
	}

	@Override
	public boolean deleteJob(String jobId) {
		return jobRepository.deleteJob(jobId);
	}

}
