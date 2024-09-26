package com.jansuraksha.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JansurakshaTransactions {

	private String JansurakshaUrnNumber;

	private String customer_acc_number;

	private String cif_code;

	private String bank_code;

	private String scheme;
	
	private String coi_document_code;

	private String timestamp;
	
	private int status;
	
	private String Transaction_status;
	
	
}
