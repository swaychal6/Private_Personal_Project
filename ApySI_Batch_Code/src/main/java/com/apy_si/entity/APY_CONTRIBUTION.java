package com.apy_si.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APY_CONTRIBUTION {
	
	
	private String BANK_CODE;
	private String BRANCH_CODE;
	private String SCHEME_CODE;
	private String ACCOUNT_NO;
	private String TRANSACTION_DATE;
	private String JOURNAL_NO;
	private String PRAN;
	private String TRANSACTION_TYPE;
	private String OVER_DUE;
	private String TOTAL_CONTRIBUTION;
	private String CONTRIBUTION_TYPE;
	private String CONTRIBUTION_MONTH;
	private String CONTRIBUTION_YEAR;
	private String TELLER_NO;
	private String CIF_CODE;
	private String LAST_MODIFIED;
	private String ERROR_MESSAGE;

//	BANK_CODE, BRANCH_CODE, SCHEME_CODE, ACCOUNT_NO, TRANSACTION_DATE,"
//			+ " JOURNAL_NO, PRAN, TRANSACTION_TYPE, OVER_DUE, TOTAL_CONTRIBUTION, CONTRIBUTION_TYPE, CONTRIBUTION_MONTH,"
//			+ " CONTRIBUTION_YEAR, TELLER_NO, CIF_CODE, LAST_MODIFIED, ERROR_MESSAGE
}
