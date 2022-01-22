package com.company.division.view;

import io.swagger.annotations.Api;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Api
public class CustomerError {

	private String errorCode;
	private String message;
}
