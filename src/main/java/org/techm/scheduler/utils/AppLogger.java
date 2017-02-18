package org.techm.scheduler.utils;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Logger for the application. This is associated with two handler
 * <object>fileHandler</object> and <object>consoleHandler</object>. the default
 * log level is INFO.
 * 
 * @author Himanshu
 *
 */
public class AppLogger {
	private static Logger logger = Logger.getLogger("org.techm.scheduler.*");

	static {
		try {
			SimpleFormatter simpleFormatter = new SimpleFormatter();

			// Update the file name when deploy in cloud
			FileHandler fileHandler = new FileHandler("/scheduler.log");
			fileHandler.setFormatter(simpleFormatter);
			logger.addHandler(fileHandler);

			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(simpleFormatter);
			logger.addHandler(consoleHandler);

			logger.setLevel(Level.INFO);

		} catch (SecurityException | IOException exp) {

			exp.printStackTrace();
		}
	}
	
	public static Logger getAppLogger(){
		return logger;
	}
}
