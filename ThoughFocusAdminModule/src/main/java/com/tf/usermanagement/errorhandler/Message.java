package com.tf.usermanagement.errorhandler;
import org.springframework.http.HttpStatus;
public class Message {
	
	private String statusCode;
	private String message;
	private String developerMsg;
	private String exception;
	
	
	public Message(String statusCode, String message, String developerMsg,
			String exception) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.developerMsg = developerMsg;
		this.exception = exception;
	}
	
	public Message() {
		super();
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the developerMsg
	 */
	public String getDeveloperMsg() {
		return developerMsg;
	}
	/**
	 * @param developerMsg the developerMsg to set
	 */
	public void setDeveloperMsg(String developerMsg) {
		this.developerMsg = developerMsg;
	}
	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}
	/**
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}
	
	public static MessageBuilder statusCode(HttpStatus httpStatus){
		return new MessageBuilder(httpStatus);
	}

	@Override
	public String toString() {
		return "Message [statusCode=" + statusCode + ", message=" + message
				+ ", developerMsg=" + developerMsg + ", exception=" + exception
				+ "]";
	}
	
	

}