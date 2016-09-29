package com.tf.usermanagement.exception;

public class UserException extends RuntimeException{

	private static final long serialVersionUID = 5169567296344844029L;
	
	public static final String USER_EXCEPTION = "User exception has occured";
	
	public static final String INVALID_DATA = "Invalid data : %s";
	
	private String message;
	
	public  UserException(){
		message = USER_EXCEPTION;
	}
	
	public UserException(String message) {
		this.message = message;
	}

	public UserException(String message, Object... args) {
		this.message = String.format(message, args);
	}

	@Override
	public String getMessage() {
		return message;
	}

}
