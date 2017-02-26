package org.techm.scheduler.respository.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.exception.DaoException;
import org.techm.scheduler.respository.JobRepository;
import org.techm.scheduler.utils.HibernateUtils;

/**
 * Implementation of <interface>JobRepository</interface>. It perform CRUD
 * operation with database.
 * 
 * @author Himanshu
 */
public class JobRepositoryImpl implements JobRepository {

	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(JobRepositoryImpl.class);

	/**
	 * Saving of <object>job</object> into database.
	 * 
	 * @param job
	 *            object to be saved into database.
	 * @return job saved job object.
	 * @throws DaoException
	 *             throw when unable to saved <object>job</object>.
	 */
	@Override
	public Job createJob(Job job) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Job savedJob = null;
		
		logger.info("Saving of job object into database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			if (job.getId() == null || job.getId().trim().length() == 0) {
				job.setId(UUID.randomUUID().toString());
			}

			session.beginTransaction();

			String strId = (String) session.save(job);
			savedJob = session.get(Job.class, strId);

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not save Job due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedJob;
	}

	/**
	 * Updating of <object>job</object> into database.
	 * 
	 * @param job
	 *            object to be updated into database.
	 * @return job updated job object.
	 * @throws DaoException
	 *             throw when unable to update <object>job</object>.
	 */
	@Override
	public Job updateJob(Job job) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Job savedJob = null;
		
		logger.info("Updating of job object into database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.update(job);

			savedJob = session.get(Job.class, job.getId());

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not update Job due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedJob;
	}

	/**
	 * Retrieve of <object>job</object> object from database based on given
	 * jobId..
	 * 
	 * @param jobId
	 *            Id of job which need to fetch from database.
	 * @return job saved job object.
	 * @throws DaoException
	 *             throw when unable to fetched <object>job</object>.
	 */
	@Override
	public Job getJobById(String jobId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Job job = null;
		
		logger.info("Retrieve the job object from database. JobId: " + jobId);
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			job = session.get(Job.class, jobId);

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not retrive Job due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return job;
	}

	/**
	 * Retrieve of all <object>job</object> objects from database.
	 * 
	 * @return list of job objects.
	 * @throws DaoException
	 *             throw when unable to fetched <object>job</object>.
	 */
	@Override
	public List<Job> getAllJobs() {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Job> listOfJob = null;
		
		logger.info("Retrieve all job objects from database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			listOfJob = session.createQuery("from Job", Job.class).getResultList();

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not retrive all jobs due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listOfJob;
	}

	/**
	 * Deletion of <object>job</object> objects from database based on given
	 * jobId.
	 * 
	 * @param jobId
	 *            Id of job which need to fetch from database.
	 * @return boolean true when the object is deleted otherwise false.
	 * @throws DaoException
	 *             throw when unable to fetched <object>config</object>.
	 */
	@Override
	public boolean deleteJob(String jobId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Job job = null;
		boolean bool = false;
		
		logger.info("Delete the job objects from database. JobId :" + jobId);
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			job = session.get(Job.class, jobId);
			session.delete(job);

			session.getTransaction().commit();
			bool = true;

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not delete Job due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return bool;
	}

}
