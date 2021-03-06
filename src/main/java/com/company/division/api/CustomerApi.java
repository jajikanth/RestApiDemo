package com.company.division.api;

import javax.validation.Valid;

import com.company.division.view.CustomerError;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.division.error.CustomerException;
import com.company.division.view.CustomerInfo;
import com.company.division.view.CustomerResponse;


@RequestMapping("/v1/customer")
public interface CustomerApi {
	
	@GetMapping("/{ip}")
	ResponseEntity<CustomerInfo> getCustomerDetails(@PathVariable String ip) throws CustomerException;

	@ApiOperation(value = "Register a customer from Canada")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Service Validation Errors", response = CustomerError.class),
			@ApiResponse(code = 200, message = "Successful retrieval",response = CustomerResponse.class) })
	@PostMapping
	ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerInfo customerInfo) throws Exception;
	
}
