package com.jansuraksha.services;

import org.springframework.stereotype.Service;

import com.jansuraksha.daoImpl.CommonRepoDaoImpl;
import com.jansuraksha.dto.GetCustomerDetailDto;
import com.jansuraksha.utils.CommonUtility;
import com.jansuraksha.utils.EntityMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PmCommonService {

	private final CommonRepoDaoImpl<?> commonRepoDaoImpl;
	
	
	public <T>Object getCustomerDetailsByScheme(GetCustomerDetailDto customerDetailDto) throws Exception {
		Object tableByScheme = CommonUtility.getTableByScheme(customerDetailDto.getScheme());
		Object accountDetails = commonRepoDaoImpl.getAccountDetails(customerDetailDto,tableByScheme.getClass());
		return accountDetails;
	}
	
	
	
	
	
	
}
