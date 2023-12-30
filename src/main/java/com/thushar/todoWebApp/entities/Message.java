package com.thushar.todoWebApp.entities;

/*
	the message class is used to create custom message and send it to the UI
 */
public class Message {
	
	private String message;
	private String errorType;

	/*
	 * Constructor which accepts message and type of error(success, error)
	 */
	public Message(String message, String errorType) {
		this.message = message;
		this.errorType = errorType;
	}

	/*
	 * empty dummy constructor which is used to invoke getter and setter methods
	 */
	public Message() {}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorType() {
		return errorType;
	}
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

}
