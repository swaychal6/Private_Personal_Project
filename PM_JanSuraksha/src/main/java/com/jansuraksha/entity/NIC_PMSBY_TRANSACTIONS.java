package com.jansuraksha.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NIC_PMSBY_TRANSACTIONS {
	private String TRAN_NUMBER;
	private String COMPANY_NAME;
	private String PREMIUM_AMOUNT;
	private String EXPIRY_DATE;
	private String CIF_CODE;
	private String BANK_CODE;
	private String BRANCH_CODE;
	private String DOB;
	private String TRAN_DATE;
	private String PRODUCT_NAME;
	private String CUSTOMER_ACC_NUMBER;
	private String MODE_OF_PAYMENT;
	private String TXN_TIME;
	private String TELLER_ID;
	private String COMPANY_ACC_NUMBER;
	private String CBS_ERROR_DESCRIPTION;
	private String JOURNAL_NUMBER;
	private String TXN_ID;
	private String CBS_ERROR_CODE;
	private String NARRATIVE;
	private String SUPDATETIME;
	private String SUPERVISORID;
	private String PINCODE;
	private String MOBILE;
	private String ACCT_HOLDER_NAME;
	private String NIC_MASTER_POLICY;
	private String BRANCH_IFSC;
	private String ACCT_HOME_BRANCH;
	private String GENDER;
	private String AADHAR;
	private String PANCARD;
	private String EMAILID;
	private String ACCT_OPEN_DATE;
	private String NOMINEE_NAME;
	private String NOMINEE_DOB;
	private String NOMINEE_REL;
	private String POLICY_STATUS;
	private String CITY;
	private String CUSTOMER_ADDRESS;
	private String CUSTOMER_DISABILITY;
	private String CUSTOMER_DISABILITYDET;
	private String CUSTOMER_ACC_TYPE;
	private String ACCT_HOLDER_FIRSTNAME;
	private String ACCT_HOLDER_MIDDLENAME;
	private String ACCT_HOLDER_LASTNAME;
	private String BC_CODE;
	private String SCHEMENAME;
	private String FLAG7;
	private String DATENODE;
	private String ACCNTNUMBER2;
	private String TXNAMOUNT1;
	private String AMOUNT1;
	private String ACCNTNUMBER1;
	private String TRANNO;
	private String SCREENNO;
	private String DEACTIVATED_TIME;
	private String APPOINTEE_NAME;
	private String APPOINTEE_DOB;
	private String APPOINTEE_ADDR;
}
