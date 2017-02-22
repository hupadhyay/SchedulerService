package org.techm.scheduler.respository.impl;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.techm.scheduler.domain.Config;
import org.techm.scheduler.exception.DaoException;
import org.techm.scheduler.respository.ConfigRepository;
import org.techm.scheduler.utils.HibernateUtils;

public class ConfigRepositoryImpl implements ConfigRepository {

	@Override
	public Config createConfig(Config config) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Config savedConfig = null;
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
			throw new DaoException("Could not save Config due to internal issue.", exp.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedConfig;
	}

	@Override
	public Config updateConfig(Config config) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Config savedConfig = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			session.update(config);

			savedConfig = session.get(Config.class, config.getId());

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			throw new DaoException("Could not update Config due to internal issue.", exp.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return savedConfig;
	}

	@Override
	public Config getConfigById(String configId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Config config = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			config = session.get(Config.class, configId);

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			throw new DaoException("Could not retrive Config due to internal issue.", exp.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return config;
	}

	@Override
	public List<Config> getAllConfigs() {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Config> listOfConfigs = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();

			session.beginTransaction();

			listOfConfigs = session.createQuery("from Config", Config.class).getResultList();

			session.getTransaction().commit();

		} catch (Exception exp) {
			exp.printStackTrace();
			throw new DaoException("Could not retrive all Configs due to internal issue.", exp.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listOfConfigs;
	}

	@Override
	public boolean deleteConfig(String configId) {
		SessionFactory sessionFactory = null;
		Session session = null;
		Config config = null;
		boolean bool = false;
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
			throw new DaoException("Could not delete Config due to internal issue.", exp.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return bool;
	}

	@Override
	public List<Config> getConfigsForStatus(String dimOrOnOff) {
		SessionFactory sessionFactory = null;
		Session session = null;
		List<Config> listOfConfigs = null;
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
			throw new DaoException("Could not retrive all Configs due to internal issue.", exp.getCause());
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return listOfConfigs;
	}

}
