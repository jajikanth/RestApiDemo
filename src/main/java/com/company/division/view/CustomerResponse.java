package com.company.division.view;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerResponse {
	
	private UUID userId;
	
	private String username;
	
	private String message;
	
	private String city;
	

}
