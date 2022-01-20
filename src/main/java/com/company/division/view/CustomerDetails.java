package com.company.division.view;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class CustomerDetails {

	@NotBlank
	private String username;
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[_#$%.]).{8,}$")
	private String password;
	@NotBlank
	private String ipAdress;
	
	
}
