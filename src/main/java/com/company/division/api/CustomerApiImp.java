package com.company.division.api;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.company.division.service.CustomerServiceImpl;
import com.company.division.view.CustomerDetails;
import com.company.division.view.CustomerResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerApiImp implements CustomerApi{
	
	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@Override
	public ResponseEntity<CustomerResponse> getCustomer(String customerId) {
		
		CustomerResponse customerResponse = CustomerResponse.builder()
															.userId(UUID.randomUUID())
															.city("CityName")
															.message("Welcome")
															.username("Jajikanth").build();
				log.info("-------test-----");
		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustomerResponse> createCustomer(CustomerDetails customerDetails) {
		// TODO Auto-generated method stub
		return null;
	}

}
