package com.company.division.error;

import lombok.Data;

@Data
public class CustomerException extends Exception {

	private String errorCode;
	private String errorMessage;

	private static final long serialVersionUID = 1L;

	public CustomerException(String errorCode, String errorMessage) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}



}
