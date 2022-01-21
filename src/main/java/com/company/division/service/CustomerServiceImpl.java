package com.company.division.service;

import static com.company.division.util.Constants.WELCOME_MSG;

import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.company.division.error.CustomerException;
import com.company.division.util.ErrorCode;
import com.company.division.view.CustomerInfo;
import com.company.division.view.CustomerIpInfo;
import com.company.division.view.CustomerResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Jajikanth
 *
 */
@Service
@Slf4j
public class CustomerServiceImpl {

	   @Autowired
	   @Qualifier("ip-api-rest-client")
	   RestTemplate restTemplate;
	
	   /**
	    * 
	    * @param customerInfo  {@link CustomerInfo}
	    * @return CustomerResponse {@link CustomerResponse}
	    * @throws CustomerException
	    */
	public CustomerResponse createCustomer(CustomerInfo customerInfo) throws CustomerException {

		CustomerResponse customerResponse;
		customerResponse = CustomerResponse.builder()
				.userId(UUID.randomUUID())
				.username(customerInfo.getUsername())
				.message(WELCOME_MSG)
				.city(getCity(customerInfo.getIpAdress())).build();
		return customerResponse;
	}
	
	/**
	 * 
	 * @param ipAdress
	 * @return city for given ip if in CA
	 * @throws CustomerException
	 */

	private String getCity(String ipAdress) throws CustomerException {
		String result = "";
		CustomerIpInfo customerIpInfo = getCustomerIpInfo(ipAdress);
			if(!"CA".equals(customerIpInfo.getCountryCode())){
				throw new CustomerException(ErrorCode.INVALID_COUNTRY.name(), ErrorCode.INVALID_COUNTRY.getMessage());
			}
			result = customerIpInfo.getCity();
		return result;
	}

	/**
	 * @param ipAdress
	 * @throws CustomerException 
	 */
	public CustomerIpInfo getCustomerIpInfo(String ipAdress) throws CustomerException {
		log.info("request ip : {}", ipAdress);
		CustomerIpInfo customerIpInfo = null;
		String url = "http://ip-api.com/json/";
		try{
			  HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity <String> entity = new HttpEntity<String>(headers);
		      
		      customerIpInfo =  restTemplate.exchange(url.concat(ipAdress), HttpMethod.GET, entity, CustomerIpInfo.class).getBody();
		      log.info(" received data from ip-api : {}", customerIpInfo);
		      
		} catch(Exception ex){
			log.error("Error Connecting to ip-api", ex);
			throw new CustomerException(ErrorCode.INTERNAL_SERVER_ERROR.name(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
		}
		if(customerIpInfo == null || !"SUCCESS".equalsIgnoreCase(customerIpInfo.getStatus()) 
								  || !StringUtils.equals(ipAdress, customerIpInfo.getQuery())){
			log.error("Error validating the response from ip-api for ip : {}", ipAdress);
			throw new CustomerException(ErrorCode.INTERNAL_SERVER_ERROR.name(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
		}
		return customerIpInfo;
	}

}
