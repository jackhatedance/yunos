package com.driverstack.yunos.remote.exception;

public class RemoteError {
	private String name;
	private String fullName;

	private String message;
	private String detailedMessage;

	public RemoteError(String name, String fullName, String message,
			String detailedMessage) {
		this.name = name;
		this.fullName = fullName;
		this.message = message;
		this.detailedMessage = detailedMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailedMessage() {
		return detailedMessage;
	}

	public void setDetailedMessage(String detailedMessage) {
		this.detailedMessage = detailedMessage;
	}

}
