/**
 * 
 */
package com.company.division.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.division.view.CustomerDetails;
import com.company.division.view.CustomerResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

/**
 * @author Jajikanth
 *
 */

@RequestMapping("/v1/customer")
public interface CustomerApi {
	
	@GetMapping("/{customerId}")
	ResponseEntity<CustomerResponse> getCustomer(@PathVariable("customerId") String customerId);
	
	@PostMapping
	ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerDetails customerDetails);
	
}
