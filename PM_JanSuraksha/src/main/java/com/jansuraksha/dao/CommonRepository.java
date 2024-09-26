package com.jansuraksha.dao;

import java.util.List;

import com.jansuraksha.dto.GetCustomerDetailDto;
import com.jansuraksha.entity.JansurakshaTransactions;


public interface CommonRepository<T> {

	List<?> getAllTransactionDetails(String bankCode);
	
	int updateJansurakshaUrnTransactions(JansurakshaTransactions jansurakshaTransactions);
	
	Object getAccountDetails(GetCustomerDetailDto customerDetailDto,Class<?>clazz);
	
	int updateAcStatus(GetCustomerDetailDto customerDetailDto);
}
