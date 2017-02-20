package org.techm.scheduler.respository.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.techm.scheduler.domain.Job;
import org.techm.scheduler.respository.JobRepository;
import org.techm.scheduler.utils.HibernateUtils;

public class JobRepositoryImpl implements JobRepository {

	@Override
	public Job createJob(Job job) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Job savedJob = null;
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
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedJob;
	}

	@Override
	public Job updateJob(Job job) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Job savedJob = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.update(job);

			savedJob = session.get(Job.class, job.getId());

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedJob;
	}

	@Override
	public Job getJobById(String jobId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Job job = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			job = session.get(Job.class, jobId);

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return job;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Job> getAllJob() {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Job> listOfJob = null;
		try{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			
			listOfJob = session.createQuery("from Job").getResultList();
			
			session.getTransaction().commit();
			
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return listOfJob;
	}

	@Override
	public boolean deleteJob(String jobId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Job job = null;
		boolean bool = false;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			
			job = session.get(Job.class, jobId);
			session.delete(job);

			session.getTransaction().commit();
			bool = true;

		} catch (Exception exp) {
			exp.printStackTrace();
			bool = false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return bool;
	}

}
