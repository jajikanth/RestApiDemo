package com.company.division.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.company.division.error.CustomerException;
import com.company.division.service.CustomerServiceImpl;
import com.company.division.view.CustomerInfo;
import com.company.division.view.CustomerResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CustomerApiImp implements CustomerApi  {

	@Autowired
	CustomerServiceImpl customerServiceImpl;

	@Override
	public ResponseEntity<CustomerInfo> getCustomerDetails(String ip) throws CustomerException {

		CustomerInfo customerInfo = CustomerInfo.builder().username("jajikanth").password("PassW0rd")
				.ipAdress(customerServiceImpl.getCustomerIpInfo(ip).getQuery()).build();
		log.info("Sample request data CustomerInfo : {}", customerInfo);

		return new ResponseEntity<CustomerInfo>(customerInfo, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CustomerResponse> createCustomer(CustomerInfo customerInfo) throws CustomerException {
		long startTime = System.currentTimeMillis();
		log.info("Received createCustomer at : {} Data : {}", startTime, customerInfo);
		CustomerResponse customerResponse;
		try {
			customerResponse = customerServiceImpl.createCustomer(customerInfo);
		} finally {
			long responseTime = System.currentTimeMillis() - startTime;
			log.info("createCustomer responseTime : {}", responseTime);
		}

		return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK);
	}

}
