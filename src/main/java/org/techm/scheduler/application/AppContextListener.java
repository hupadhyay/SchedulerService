package org.techm.scheduler.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of <interface>ServletContextListener</interface> to start
 * quartz Scheduler after loading of rest application to servlet container.
 * 
 * @author Himanshu
 *
 */
@WebListener
public class AppContextListener implements ServletContextListener {
	
	Logger logger = LoggerFactory.getLogger(AppContextListener.class);

	/**
	 * This method is called when Servlet container is shutdown.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		logger.info("context destroyed");

	}

	/**
	 * This method is called after loading of rest application to Servlet
	 * Container. It starts the default Quartz Scheduler.
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {

		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// Start the scheduler after two second of loading it.
			scheduler.startDelayed(2);
			logger.info("Quartz Scheduler has been started.");
		} catch (SchedulerException exp) {
			exp.printStackTrace();
			logger.error("Error occurs while starting the quartz Scheduler. Message: %s", exp.getMessage());
			throw new org.techm.scheduler.exception.SchedulerException("Could not start Scheduler.", exp.getCause());
		}

	}

}
