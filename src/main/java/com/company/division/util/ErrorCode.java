package com.company.division.util;

public enum ErrorCode {
	INVALID_PAYLOAD("Unsupported"),
	INVALID_COUNTRY("Not allowed to create from given location."), 
	INTERNAL_SERVER_ERROR("Unable to validate location.");

	private final String message;

	public String getMessage() {
		return message;
	}

	private ErrorCode(String message) {
		this.message = message;
	}
}
