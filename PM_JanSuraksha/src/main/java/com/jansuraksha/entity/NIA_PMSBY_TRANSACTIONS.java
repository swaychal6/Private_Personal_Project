package com.jansuraksha.entity;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NIA_PMSBY_TRANSACTIONS {
	private String ACCNTNUMBER1;
	private String ACCNTNUMBER2;
	private String ACCT_OPEN_DATE;
	private String ACTIVE_BANK_ACCOUNT;
	private String AMOUNT1;
	private String APPLICATION_DT;
	private String APPLICATION_ID;
	private String BANK_CODE;
	private String BASEPREMIUM;
	private String BCSTATUS;
	private String BRANCH_CODE;
	private String BRANCH_IFSC;
	private String CBS_ERROR_CODE;
	private String CBS_ERROR_DESCRIPTION;
	private String CIF_CODE;
	private String COMPANY_ACC_NUMBER;
	private String COMPANY_NAME;
	private String CUSTOMER_ACC_NUMBER;
	private String DATENODE;
	private String DEACTIVATED_TIME;
	private String EXPIRY_DATE;
	private String FLAG7;
	private String GENDER;
	private String INSUREDAADHAAR;
	private String INSUREDADDRESS;
	private String INSUREDCITY;
	private String INSUREDDOB;
	private String INSUREDGENDER;
	private String INSUREDNAME;
	private String INSUREDPAN;
	private String INSUREDPIN;
	private String INSUREDSTATE;
	private String JOURNAL_NUMBER;
	private String MODE_OF_PAYMENT;
	private String NARRATIVE;
	private String NETPREMIUM;
	private String NETSERVICETAX;
	private String NIA_MASTER_POLICY;
	private String NOMINEE_DOB;
	private String NOMINEE_NAME;
	private String NOMINEE_REL;
	private String ORGANISATION_BRANCH;
	private String ORGANISATION_ID;
	private String ORGANISATION_IFSC;
	private String PARTY_ID;
	private String POLICYNUM;
	private String POLICY_ENDDT;
	private String POLICY_STARTDT;
	private String POLICY_STATUS;
	private String PREMIUM_AMOUNT;
	private String PREVAPPID;
	private String PRODUCT_NAME;
	private String SCHEMENAME;
	private String SUPDATETIME;
	private String SUPERVISORID;
	private String TELLER_ID;
	private String TITLE;
	private String TOTPREMIUM;
	private String TRANNO;
	private String TRAN_DATE;
	private String TRAN_NUMBER;
	private String TXNAMOUNT1;
	private String TXN_ID;
	private String TXN_TIME;
}
