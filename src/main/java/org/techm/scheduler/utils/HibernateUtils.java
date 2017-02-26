package org.techm.scheduler.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton class for creating <object>sessionfactory</object> and maintaining
 * it. This class will create object of <class>SessionFactory</class> at time of
 * loading of class.
 *
 */
public class HibernateUtils {

	/** Logger instance to log the incidents. */
	private static Logger logger = LoggerFactory.getLogger(HibernateUtils.class);

	/** Create Object of sessionFactory at time of loading of class. */
	private static SessionFactory sessionFactory = buildSessionFactory();

	/**
	 * Private factory method to create object of <class>SessionFactory</class>.
	 * 
	 * @return sessionFactory
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			if (sessionFactory == null) {
				logger.info("Loading of hibernate configuration from cfg file and creating sessionFactory object.");
				Configuration configuration = new Configuration();
				configuration.configure();
				sessionFactory = configuration.buildSessionFactory();
				logger.info("SessionFactory object has been created.");
			}
			return sessionFactory;
		} catch (Throwable ex) {
			logger.error("Initial SessionFactory creation failed." + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	/**
	 * Getter method of <object>sessionFactory</object>
	 * 
	 * @return sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Close the session factory and release the resources.
	 */
	public static void shutdown() {
		logger.info("Closing of SessionFactory object");
		getSessionFactory().close();
	}
}
