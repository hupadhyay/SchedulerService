package org.techm.scheduler.exception;

/**
 * 
 * @author Himanshu
 *
 */
public class SchedulerException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor.
	 */
	public SchedulerException() {
		super();
	}

	/**
	 * Constructor with exception message.
	 * 
	 * @param msg
	 */
	public SchedulerException(String msg) {
		super(msg);
	}

	/**
	 * Constructor with cause <object>throwable</object> of exception.
	 * 
	 * @param throwable
	 */
	public SchedulerException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructor with Cause <object>throwable</object> and message of exception.
	 * @param msg
	 * @param throwable
	 */
	public SchedulerException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

}
