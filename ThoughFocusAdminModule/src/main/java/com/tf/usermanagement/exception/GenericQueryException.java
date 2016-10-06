package com.tf.usermanagement.exception;
/**
 * Generic Exception class to throw if query fails to execute
 * @author Utpal Kant
 *
 */
public class GenericQueryException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8772691200727650369L;

	/**
	 * 
	 */

	private  String message;
	
	public static final String QUERY_FAILED_EXCEPTION="Could not process query : ";
	
	public GenericQueryException(String message) {
		super();
		this.message = message;
	}



	public GenericQueryException() {

		super();
		message = QUERY_FAILED_EXCEPTION;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericQueryException [message=" + message + "]";
	}
}
