package org.techm.scheduler.respository.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.respository.TriggerRepository;
import org.techm.scheduler.utils.HibernateUtils;

public class TriggerRepositoryImpl implements TriggerRepository{

	@Override
	public Trigger createTrigger(Trigger trigger) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Trigger savedTrigger = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			if (trigger.getId() == null || trigger.getId().trim().length() == 0) {
				trigger.setId(UUID.randomUUID().toString());
			}

			session.beginTransaction();

			String strId = (String) session.save(trigger);
			savedTrigger = session.get(Trigger.class, strId);
			
			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedTrigger;
	}

	@Override
	public Trigger updateTrigger(Trigger trigger) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Trigger savedTrigger = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.update(trigger);

			savedTrigger = session.get(Trigger.class, trigger.getId());

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedTrigger;
	}

	@Override
	public Trigger getTriggerById(String triggerId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Trigger trigger = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			trigger = session.get(Trigger.class, triggerId);

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return trigger;
	}

	@Override
	public List<Trigger> getAllTriggers() {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Trigger> listOfTrigger = null;
		try{
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			
			listOfTrigger = session.createQuery("from Trigger").getResultList();
			
			session.getTransaction().commit();
			
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
			if(session != null){
				session.close();
			}
		}
		return listOfTrigger;
	}

	@Override
	public boolean deleteTrigger(String triggerId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Trigger trigger = null;
		boolean bool = false;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			
			trigger = session.get(Trigger.class, triggerId);
			session.delete(trigger);

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
