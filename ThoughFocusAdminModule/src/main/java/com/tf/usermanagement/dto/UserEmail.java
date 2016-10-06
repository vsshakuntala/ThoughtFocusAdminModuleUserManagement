package com.tf.usermanagement.dto;

import java.io.Serializable;

public class UserEmail implements Serializable{
	
	public UserEmail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;

	public UserEmail(String email) {
		super();
		this.email = email;
	}

	public String getEmailId() {
		return email;
	}

	public void setEmailId(String emailId) {
		this.email = emailId;
	}

	@Override
	public String toString() {
		return "UserEmail [emailId=" + email + "]";
	}
	
	

}
