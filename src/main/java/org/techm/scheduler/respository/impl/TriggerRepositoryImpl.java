package org.techm.scheduler.respository.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Trigger;
import org.techm.scheduler.exception.DaoException;
import org.techm.scheduler.respository.TriggerRepository;
import org.techm.scheduler.utils.HibernateUtils;

/**
 * Implementation of <interface>TriggerRepository</interface>. It perform CRUD
 * operation with database.
 * 
 * @author Himanshu
 */
public class TriggerRepositoryImpl implements TriggerRepository {

	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(TriggerRepositoryImpl.class);

	/**
	 * Saving of <object>trigger</object> into database.
	 * 
	 * @param trigger
	 *            object to be saved into database.
	 * @return trigger saved trigger object.
	 * @throws DaoException
	 *             throw when unable to saved <object>trigger</object>.
	 */
	@Override
	public Trigger createTrigger(Trigger trigger) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Trigger savedTrigger = null;
		
		logger.info("Saving of trigger object into database.");
		
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
			String errorMsg = "Could not save trigger due to internal issue." + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedTrigger;
	}

	/**
	 * Updating of <object>trigger</object> into database.
	 * 
	 * @param trigger
	 *            object to be updated into database.
	 * @return trigger updated trigger object.
	 * @throws DaoException
	 *             throw when unable to update <object>trigger</object>.
	 */
	@Override
	public Trigger updateTrigger(Trigger trigger) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Trigger savedTrigger = null;
		
		logger.info("Updating of trigger object into database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.update(trigger);

			savedTrigger = session.get(Trigger.class, trigger.getId());

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not update trigger due to internal issue." + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedTrigger;
	}

	/**
	 * Retrieve of <object>trigger</object> object from database based on given
	 * triggerId..
	 * 
	 * @param triggerId
	 *            Id of trigger which need to fetch from database.
	 * @return trigger saved trigger object.
	 * @throws DaoException
	 *             throw when unable to fetched <object>trigger</object>.
	 */
	@Override
	public Trigger getTriggerById(String triggerId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Trigger trigger = null;
		
		logger.info("Retrieve the trigger object from database. TriggerId: " + triggerId);
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			trigger = session.get(Trigger.class, triggerId);

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not retrive trigger due to internal issue." + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return trigger;
	}

	/**
	 * Retrieve of all <object>trigger</object> objects from database.
	 * 
	 * @return list of trigger objects.
	 * @throws DaoException
	 *             throw when unable to fetched <object>trigger</object>.
	 */
	@Override
	public List<Trigger> getAllTriggers() {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Trigger> listOfTrigger = null;
		
		logger.info("Retrieve all trigger objects from database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			listOfTrigger = session.createQuery("from Trigger", Trigger.class).getResultList();

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not get all triggers due to internal issue." + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listOfTrigger;
	}

	/**
	 * Deletion of <object>trigger</object> objects from database based on given
	 * triggerId.
	 * 
	 * @param triggerId
	 *            Id of trigger which need to fetch from database.
	 * @return boolean true when the object is deleted otherwise false.
	 * @throws DaoException
	 *             throw when unable to fetched <object>trigger</object>.
	 */
	@Override
	public boolean deleteTrigger(String triggerId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Trigger trigger = null;
		boolean bool = false;
		
		logger.info("Delete the trigger objects from database. JobId :" + triggerId);
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			trigger = session.get(Trigger.class, triggerId);
			session.delete(trigger);

			session.getTransaction().commit();
			bool = true;

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not delete trigger due to internal issue." + exp.getCause();
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
