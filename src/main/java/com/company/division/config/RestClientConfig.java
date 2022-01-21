package com.company.division.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
	
	
	@Bean("ip-api-rest-client")
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}

}
