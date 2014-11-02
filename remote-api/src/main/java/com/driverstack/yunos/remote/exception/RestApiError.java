package com.driverstack.yunos.remote.exception;

public class RestApiError {

	private String message;

	public RestApiError(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
