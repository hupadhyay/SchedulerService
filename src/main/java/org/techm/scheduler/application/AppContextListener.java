package org.techm.scheduler.application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

@WebListener
public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("context destroyed");

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		try {
			Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

			// Start the scheduler after two second of loading it.
			scheduler.startDelayed(2);
			System.out.println("Scheduler has started.");
		} catch (SchedulerException exp) {
			exp.printStackTrace();
			throw new org.techm.scheduler.exception.SchedulerException("Could not start Scheduler.", exp.getCause());
		}

	}

}
