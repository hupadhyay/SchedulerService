package org.techm.scheduler.respository.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.techm.scheduler.domain.Config;
import org.techm.scheduler.exception.DaoException;
import org.techm.scheduler.respository.ConfigRepository;
import org.techm.scheduler.utils.HibernateUtils;

/**
 * Implementation of <interface>ConfigRepository</interface>. It perform CRUD
 * operation with database.
 * 
 * @author Himanshu
 */
public class ConfigRepositoryImpl implements ConfigRepository {
	
	/** Logger instance to log the incidents. */
	Logger logger = LoggerFactory.getLogger(ConfigRepositoryImpl.class);

	/**
	 * Saving of <object>config</object> into database.
	 * 
	 * @param config
	 *            object to be saved into database.
	 * @return config 
	 * 			  saved config object.
	 * @throws DaoException
	 *             throw when unable to saved <object>config</object>.
	 */
	@Override
	public Config createConfig(Config config) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Config savedConfig = null;

		logger.info("Saving of configuration object into database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			if (config.getId() == null || config.getId().trim().length() == 0) {
				config.setId(UUID.randomUUID().toString());
			}

			session.beginTransaction();

			String strId = (String) session.save(config);
			savedConfig = session.get(Config.class, strId);

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not save Config due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedConfig;
	}

	/**
	 * Updating of <object>config</object> into database.
	 * 
	 * @param job
	 *            object to be updated into database.
	 * @return job 
	 *            updated job object.
	 * @throws DaoException
	 *             throw when unable to update <object>job</object>.
	 */
	@Override
	public Config updateConfig(Config config) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Config savedConfig = null;
		
		logger.info("Updating of configuration object into database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.update(config);

			savedConfig = session.get(Config.class, config.getId());

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not update Config due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedConfig;
	}

	/**
	 * Retrieve of <object>config</object> object from database based on given
	 * configId..
	 * 
	 * @param configId
	 *            Id of config which need to fetch from database.
	 * @return config 
	 * 			saved config object.
	 * @throws DaoException
	 *             throw when unable to fetched <object>config</object>.
	 */
	@Override
	public Config getConfigById(String configId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Config config = null;
		
		logger.info("Retrieving of configuration object from database. ConfigId: " + configId);
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			config = session.get(Config.class, configId);

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not retrive Config due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return config;
	}

	/**
	 * Retrieve of all <object>config</object> objects from database.
	 * 
	 * @return list of config objects.
	 * @throws DaoException
	 *             throw when unable to fetched <object>config</object>.
	 */
	@Override
	public List<Config> getAllConfigs() {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Config> listOfConfigs = null;
		
		logger.info("Retrieving of all configuration objects from database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			listOfConfigs = session.createQuery("from Config", Config.class).getResultList();

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not retrive all Configs due to internal issue. Error: " + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listOfConfigs;
	}

	/**
	 * Deletion of <object>config</object> objects from database based on given
	 * jobId.
	 * 
	 * @param configId
	 *            Id of config which need to fetch from database.
	 * @return boolean 
	 * 			  true when the object is deleted otherwise false.
	 * @throws DaoException
	 *             throw when unable to fetched <object>config</object>.
	 */
	@Override
	public boolean deleteConfig(String configId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Config config = null;
		boolean bool = false;
		
		logger.info("Deleting of configuration objects from database. ConfigId: " +  configId);
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			config = session.get(Config.class, configId);
			session.delete(config);

			session.getTransaction().commit();
			bool = true;

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not delete Config due to internal issue." + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return bool;
	}


	/**
	 * Retrieving of <object>config</object> objects from database based on dim type.
	 * 
	 * @param dimOrOnOff
	 *            weather retrieve for dim or onoff.
	 * @return List<Config> 
	 * 			  list of configuration objects.
	 * @throws DaoException
	 *             throw when unable to fetched <object>config</object>.
	 */
	@Override
	public List<Config> getConfigsForStatus(String dimOrOnOff) {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Config> listOfConfigs = null;
		
		logger.info("Deleting of configuration objects from database.");
		
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			boolean bool = false;
			if (dimOrOnOff.equalsIgnoreCase("dim")) {
				bool = true;
			}

			listOfConfigs = session.createQuery("from Config where isDim = " + bool, Config.class).getResultList();

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			String errorMsg = "Could not retrive all Configs due to internal issue." + exp.getCause();
			logger.error(errorMsg);
			throw new DaoException(errorMsg);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listOfConfigs;
	}

}
