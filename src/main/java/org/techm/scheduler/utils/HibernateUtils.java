package org.techm.scheduler.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Singleton class for creating <object>sessionfactory</object> and maintaining
 * it. This class will create object of <class>SessionFactory</class> at time of
 * loading of class.
 *
 */
public class HibernateUtils {
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
				Configuration configuration = new Configuration();
				configuration.configure();
				sessionFactory = configuration.buildSessionFactory();
			}
			return sessionFactory;
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
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
		getSessionFactory().close();
	}
}
