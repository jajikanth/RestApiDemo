package com.company.division.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerError {

	private String errorCode;
	private String message;
}
