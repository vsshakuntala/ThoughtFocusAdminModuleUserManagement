package com.tf.usermanagement.exceptions;

import java.io.Serializable;

/**
 * Throw Exception when data is not sufficient to query for request
 * @author Santosh
 *
 */
public class InsufficientDataException extends RuntimeException implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = 462683926442911220L;

    /**
     * 
     */
   
    private  String message;;
    
    public static final String NOT_FOUND_MSG="Data not sufficient to fulfil request";
	
	public InsufficientDataException(String message) {
		super();
		this.message = message;
	}



	public InsufficientDataException() {

		super();
		message = NOT_FOUND_MSG;
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
		return message;
	}


}
