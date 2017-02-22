package org.techm.scheduler.exception;

public class DaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default Constructor.
	 */
	public DaoException() {
		super();
	}

	/**
	 * Constructor with exception message.
	 * 
	 * @param msg
	 */
	public DaoException(String msg) {
		super(msg);
	}

	/**
	 * Constructor with cause <object>throwable</object> of exception.
	 * 
	 * @param throwable
	 */
	public DaoException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructor with Cause <object>throwable</object> and message of exception.
	 * @param msg
	 * @param throwable
	 */
	public DaoException(String msg, Throwable throwable) {
		super(msg, throwable);
	}
}
