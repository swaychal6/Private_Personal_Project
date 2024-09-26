package com.jansuraksha.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GetCustomerDetailDto {

	//use this pojo to get the customer data from the database table for every table
	private String customerAcc;
	
	private String cifCode;
	
	private String scheme;
	
}
