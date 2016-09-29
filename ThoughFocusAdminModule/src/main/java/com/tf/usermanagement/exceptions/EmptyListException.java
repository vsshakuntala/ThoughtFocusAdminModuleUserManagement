package com.tf.usermanagement.exceptions;

import com.tf.usermanagement.errorhandler.Message;

/**
 * This is a generic exception class which is used to
 * specify that list is empty
 * @author Manideep
 *
 */
public class EmptyListException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String messge;
	private  Message errorMessage;
	private EmptyListException exp;
	public static final String EMPTY_LIST = "List Is Empty!";
	
	
	public EmptyListException(String message){
		super();
		this.messge=message;
	}
	
	public EmptyListException(Message errorMessage,EmptyListException e) {
		this.errorMessage=errorMessage;
		this.exp=e;
	}
	
	//setters and getters
	public String getMessge() {
		return messge;
	}


	public void setMessge(String messge) {
		this.messge = messge;
	}

	public Message getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(Message errorMessage) {
		this.errorMessage = errorMessage;
	}

	public EmptyListException getExp() {
		return exp;
	}

	public void setExp(EmptyListException exp) {
		this.exp = exp;
	}
	
	

}
