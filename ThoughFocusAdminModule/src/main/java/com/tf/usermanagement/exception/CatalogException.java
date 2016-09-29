/**
 * 
 */
package com.tf.usermanagement.exception;

import com.tf.usermanagement.errorhandler.Message;

/**
 * @author abhilash
 *
 */
public class CatalogException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String messge;
	private  Message errorMessage;
	private CatalogException exp;
	
	
	public CatalogException(String message){
		super();
		this.messge=message;
	}
	
	public CatalogException(Message errorMessage,CatalogException e) {
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

	public CatalogException getExp() {
		return exp;
	}

	public void setExp(CatalogException exp) {
		this.exp = exp;
	}
	
	

}
