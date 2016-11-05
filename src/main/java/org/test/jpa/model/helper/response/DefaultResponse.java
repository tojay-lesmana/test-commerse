package org.test.jpa.model.helper.response;

public class DefaultResponse {
	
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String ERROR_MESSAGE = "An errror occured on your request, please try again !";
	public static final String SUCCESS_MESSAGE = "Successfully processed your request.";
	
	private String status;
	private String message;
	
	public DefaultResponse() {
		status = SUCCESS;
		message = SUCCESS_MESSAGE;
	}

	public DefaultResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
