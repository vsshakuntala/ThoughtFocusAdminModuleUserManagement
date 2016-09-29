package com.tf.usermanagement.errorhandler;

import java.util.Objects;

import org.springframework.http.HttpStatus;

public class MessageBuilder {

	private HttpStatus statusCode;
	private String message;
	private String developerMsg;
	private String exception;

	MessageBuilder(HttpStatus statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public static MessageBuilder statusCode(HttpStatus httpStatus) {
		return new MessageBuilder(httpStatus);
	}

	public MessageBuilder message(String message) {
		this.message = message;
		return this;
	}

	public MessageBuilder developerMsg(String developerMsg) {
		this.developerMsg = developerMsg;
		return this;
	}

	public MessageBuilder exception(String exception) {
		this.exception = exception;
		return this;
	}

	public Message build() {
		Objects.requireNonNull(this.statusCode, "statusCode must not be null");
		return new Message(this.statusCode.toString(), this.message, this.developerMsg, this.exception);
	}
}