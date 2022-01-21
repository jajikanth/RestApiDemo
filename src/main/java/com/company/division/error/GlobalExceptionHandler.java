package com.company.division.error;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.division.util.ErrorCode;
import com.company.division.view.CustomerError;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<CustomerError> customerException(CustomerException ce) {
		CustomerError customerError = CustomerError.builder()
													.errorCode(ce.getErrorCode())
													.message(ce.getErrorMessage()).build();
		return new ResponseEntity<CustomerError>(customerError, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<CustomerError>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<CustomerError> customerErrors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			customerErrors.add(CustomerError.builder().errorCode(ErrorCode.INVALID_PAYLOAD.name())
					.message(fieldName + "-" + errorMessage).build());
		});
		return new ResponseEntity<List<CustomerError>>(customerErrors, HttpStatus.BAD_REQUEST);
	}

}
