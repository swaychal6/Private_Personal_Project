package com.apy_si.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class APY_MASTER_TRANSACTIONS {
	


	private final static Logger logger = LogManager.getLogger(APY_MASTER_TRANSACTIONS.class);

	private String previous_overdueCounter;
	private String TXN_ID;
	private String BRANCH_CODE;
	private String ACCT_HOLDER_NAME;
	private String CUSTOMER_ACC_NUMBER;
	private String BRANCH_IFSC;
	private String PRAN_NO;
	private String SI_DATE;
	private String PENSION_AMT;
	private String CONTRIBUTION_AMT;
	private String TOTAL_AMT;
	private String INTEREST_AMT;
	private String TITLE;
	private String FATHERS_NAME;
	private String GENDER;
	private String DOB;
	private String PANCARD;
	private String AADHAR;
	private String DOOR_FLAT;
	private String STREET_ROAD;
	private String LOCALITY_VILLAGE;
	private String DISTRICT;
	private String CITY_TOWN;
	private String STATE;
	private String COUNTRY;
	private String PINCODE;
	private String MOBILE;
	private String TELEPHONE;
	private String EMAIL;
	private String DATE_OF_APPLICATION;
	private String DATE_OF_COMPLETION;
	private String MARITAL_STATUS;
	private String SPOUSE_NAME;
	private String SPOUSE_AADHAAR;
	private String ACNT_TYPE;
	private String BANK_CODE;
	private String BRANCH_NAME;
	private String MICR_CODE;
	private String SWAWLAMBHAN_ENROLLED;
	private String SOCIAL_SERVICE_ENROLLED;
	private String INCOME_TAX_PAYER;
	private String NOMINEE_NAME;
	private String NOMINEE_DOB;
	private String NOMINEE_AADHAR;
	private String GURDIAN_NAME;
	private String DEFAULTER_MONTH;
	private String NARRATIVE;
	private String POLICY_STATUS;
	private String TELLER_ID;
	private String TXN_TIME;
	private String COMPANY_ACC_NUMBER;
	private String NOMINEE_RELATIONSHIP;
	private String MODE_OF_PAYMENT;
	private String PLACE_OF_APPLICATION;
	private String TRAN_NUMBER;
	private String SUPERVISORID;
	private String SUPDATETIME;
	private String JOURNAL_NUMBER;
	private String CBS_ERROR_CODE;
	private String CBS_ERROR_DESCRIPTION;
	private String ACCT_HOME_BRANCH;
	private String COMPANY_NAME;
	private String PREMIUM_AMOUNT;
	private String CIF_CODE;
	private String OVERDUEAMOUNT;
	private String LANDMARK;
	private String SI_NEXT_DATE;
	private String SI_END_DATE;
	private String PENALITY_CHARGE;
	private String PENALITY_INST_COUNT;
	private String NO_OF_INST_RECD;
	private String COMMISSION_AMT;
	private String PAID_UPTO_DATE;
	private String CURR_STATUS;
	private String outputType;
	private String errorMessage;
	private String errorCode;
	private String requestString;
	private String responseString;
	private String NO_OF_INST_PENDING;
	private String CONTRIBUTION_MONTH;
	private String CONTRIBUTION_YEAR;
	private String FREQUENCY;
	private String PENALTY_COUNT_UPDATED_ON;

	public String getPENALTY_COUNT_UPDATED_ON() {
		return PENALTY_COUNT_UPDATED_ON;
	}

	public void setPENALTY_COUNT_UPDATED_ON(String pENALTYCOUNTUPDATEDON) {
		PENALTY_COUNT_UPDATED_ON = pENALTYCOUNTUPDATEDON;
	}

	public String getPrevious_overdueCounter() {
		return previous_overdueCounter;
	}

	public void setPrevious_overdueCounter(String previous_overdueCounter) {
		this.previous_overdueCounter = previous_overdueCounter;
	}

	public String getFREQUENCY() {
		return FREQUENCY;
	}

	public void setFREQUENCY(String fREQUENCY) {
		FREQUENCY = fREQUENCY;
	}

	public String getTXN_ID() {
		return TXN_ID;
	}

	public void setTXN_ID(String tXN_ID) {
		TXN_ID = tXN_ID;
	}

	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}

	public void setBRANCH_CODE(String bRANCH_CODE) {
		BRANCH_CODE = bRANCH_CODE;
	}

	public String getACCT_HOLDER_NAME() {
		return ACCT_HOLDER_NAME;
	}

	public void setACCT_HOLDER_NAME(String aCCT_HOLDER_NAME) {
		ACCT_HOLDER_NAME = aCCT_HOLDER_NAME;
	}

	public String getCUSTOMER_ACC_NUMBER() {
		return CUSTOMER_ACC_NUMBER;
	}

	public void setCUSTOMER_ACC_NUMBER(String cUSTOMER_ACC_NUMBER) {
		CUSTOMER_ACC_NUMBER = cUSTOMER_ACC_NUMBER;
	}

	public String getBRANCH_IFSC() {
		return BRANCH_IFSC;
	}

	public void setBRANCH_IFSC(String bRANCH_IFSC) {
		BRANCH_IFSC = bRANCH_IFSC;
	}

	public String getPRAN_NO() {
		return PRAN_NO;
	}

	public void setPRAN_NO(String pRAN_NO) {
		PRAN_NO = pRAN_NO;
	}

	public String getSI_DATE() {
		return SI_DATE;
	}

	public void setSI_DATE(String sI_DATE) {
		SI_DATE = sI_DATE;
	}

	public String getPENSION_AMT() {
		return PENSION_AMT;
	}

	public void setPENSION_AMT(String pENSION_AMT) {
		PENSION_AMT = pENSION_AMT;
	}

	public String getCONTRIBUTION_AMT() {
		return CONTRIBUTION_AMT;
	}

	public void setCONTRIBUTION_AMT(String cONTRIBUTION_AMT) {
		CONTRIBUTION_AMT = cONTRIBUTION_AMT;
	}

	public String getTOTAL_AMT() {
		return TOTAL_AMT;
	}

	public void setTOTAL_AMT(String tOTAL_AMT) {
		TOTAL_AMT = tOTAL_AMT;
	}

	public String getINTEREST_AMT() {
		return INTEREST_AMT;
	}

	public void setINTEREST_AMT(String iNTEREST_AMT) {
		INTEREST_AMT = iNTEREST_AMT;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getFATHERS_NAME() {
		return FATHERS_NAME;
	}

	public void setFATHERS_NAME(String fATHERS_NAME) {
		FATHERS_NAME = fATHERS_NAME;
	}

	public String getGENDER() {
		return GENDER;
	}

	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getPANCARD() {
		return PANCARD;
	}

	public void setPANCARD(String pANCARD) {
		PANCARD = pANCARD;
	}

	public String getAADHAR() {
		return AADHAR;
	}

	public void setAADHAR(String aADHAR) {
		AADHAR = aADHAR;
	}

	public String getDOOR_FLAT() {
		return DOOR_FLAT;
	}

	public void setDOOR_FLAT(String dOOR_FLAT) {
		DOOR_FLAT = dOOR_FLAT;
	}

	public String getSTREET_ROAD() {
		return STREET_ROAD;
	}

	public void setSTREET_ROAD(String sTREET_ROAD) {
		STREET_ROAD = sTREET_ROAD;
	}

	public String getLOCALITY_VILLAGE() {
		return LOCALITY_VILLAGE;
	}

	public void setLOCALITY_VILLAGE(String lOCALITY_VILLAGE) {
		LOCALITY_VILLAGE = lOCALITY_VILLAGE;
	}

	public String getDISTRICT() {
		return DISTRICT;
	}

	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}

	public String getCITY_TOWN() {
		return CITY_TOWN;
	}

	public void setCITY_TOWN(String cITY_TOWN) {
		CITY_TOWN = cITY_TOWN;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public String getCOUNTRY() {
		return COUNTRY;
	}

	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}

	public String getPINCODE() {
		return PINCODE;
	}

	public void setPINCODE(String pINCODE) {
		PINCODE = pINCODE;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	public String getTELEPHONE() {
		return TELEPHONE;
	}

	public void setTELEPHONE(String tELEPHONE) {
		TELEPHONE = tELEPHONE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getDATE_OF_APPLICATION() {
		return DATE_OF_APPLICATION;
	}

	public void setDATE_OF_APPLICATION(String dATE_OF_APPLICATION) {
		DATE_OF_APPLICATION = dATE_OF_APPLICATION;
	}

	public String getDATE_OF_COMPLETION() {
		return DATE_OF_COMPLETION;
	}

	public void setDATE_OF_COMPLETION(String dATE_OF_COMPLETION) {
		DATE_OF_COMPLETION = dATE_OF_COMPLETION;
	}

	public String getMARITAL_STATUS() {
		return MARITAL_STATUS;
	}

	public void setMARITAL_STATUS(String mARITAL_STATUS) {
		MARITAL_STATUS = mARITAL_STATUS;
	}

	public String getSPOUSE_NAME() {
		return SPOUSE_NAME;
	}

	public void setSPOUSE_NAME(String sPOUSE_NAME) {
		SPOUSE_NAME = sPOUSE_NAME;
	}

	public String getSPOUSE_AADHAAR() {
		return SPOUSE_AADHAAR;
	}

	public void setSPOUSE_AADHAAR(String sPOUSE_AADHAAR) {
		SPOUSE_AADHAAR = sPOUSE_AADHAAR;
	}

	public String getACNT_TYPE() {
		return ACNT_TYPE;
	}

	public void setACNT_TYPE(String aCNT_TYPE) {
		ACNT_TYPE = aCNT_TYPE;
	}

	public String getBANK_CODE() {
		return BANK_CODE;
	}

	public void setBANK_CODE(String bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}

	public String getBRANCH_NAME() {
		return BRANCH_NAME;
	}

	public void setBRANCH_NAME(String bRANCH_NAME) {
		BRANCH_NAME = bRANCH_NAME;
	}

	public String getMICR_CODE() {
		return MICR_CODE;
	}

	public void setMICR_CODE(String mICR_CODE) {
		MICR_CODE = mICR_CODE;
	}

	public String getSWAWLAMBHAN_ENROLLED() {
		return SWAWLAMBHAN_ENROLLED;
	}

	public void setSWAWLAMBHAN_ENROLLED(String sWAWLAMBHAN_ENROLLED) {
		SWAWLAMBHAN_ENROLLED = sWAWLAMBHAN_ENROLLED;
	}

	public String getSOCIAL_SERVICE_ENROLLED() {
		return SOCIAL_SERVICE_ENROLLED;
	}

	public void setSOCIAL_SERVICE_ENROLLED(String sOCIAL_SERVICE_ENROLLED) {
		SOCIAL_SERVICE_ENROLLED = sOCIAL_SERVICE_ENROLLED;
	}

	public String getINCOME_TAX_PAYER() {
		return INCOME_TAX_PAYER;
	}

	public void setINCOME_TAX_PAYER(String iNCOME_TAX_PAYER) {
		INCOME_TAX_PAYER = iNCOME_TAX_PAYER;
	}

	public String getNOMINEE_NAME() {
		return NOMINEE_NAME;
	}

	public void setNOMINEE_NAME(String nOMINEE_NAME) {
		NOMINEE_NAME = nOMINEE_NAME;
	}

	public String getNOMINEE_DOB() {
		return NOMINEE_DOB;
	}

	public void setNOMINEE_DOB(String nOMINEE_DOB) {
		NOMINEE_DOB = nOMINEE_DOB;
	}

	public String getNOMINEE_AADHAR() {
		return NOMINEE_AADHAR;
	}

	public void setNOMINEE_AADHAR(String nOMINEE_AADHAR) {
		NOMINEE_AADHAR = nOMINEE_AADHAR;
	}

	public String getGURDIAN_NAME() {
		return GURDIAN_NAME;
	}

	public void setGURDIAN_NAME(String gURDIAN_NAME) {
		GURDIAN_NAME = gURDIAN_NAME;
	}

	public String getDEFAULTER_MONTH() {
		return DEFAULTER_MONTH;
	}

	public void setDEFAULTER_MONTH(String dEFAULTER_MONTH) {
		DEFAULTER_MONTH = dEFAULTER_MONTH;
	}

	public String getNARRATIVE() {
		return NARRATIVE;
	}

	public void setNARRATIVE(String nARRATIVE) {
		NARRATIVE = nARRATIVE;
	}

	public String getPOLICY_STATUS() {
		return POLICY_STATUS;
	}

	public void setPOLICY_STATUS(String pOLICY_STATUS) {
		POLICY_STATUS = pOLICY_STATUS;
	}

	public String getTELLER_ID() {
		return TELLER_ID;
	}

	public void setTELLER_ID(String tELLER_ID) {
		TELLER_ID = tELLER_ID;
	}

	public String getTXN_TIME() {
		return TXN_TIME;
	}

	public void setTXN_TIME(String tXN_TIME) {
		TXN_TIME = tXN_TIME;
	}

	public String getCOMPANY_ACC_NUMBER() {
		return COMPANY_ACC_NUMBER;
	}

	public void setCOMPANY_ACC_NUMBER(String cOMPANY_ACC_NUMBER) {
		COMPANY_ACC_NUMBER = cOMPANY_ACC_NUMBER;
	}

	public String getNOMINEE_RELATIONSHIP() {
		return NOMINEE_RELATIONSHIP;
	}

	public void setNOMINEE_RELATIONSHIP(String nOMINEE_RELATIONSHIP) {
		NOMINEE_RELATIONSHIP = nOMINEE_RELATIONSHIP;
	}

	public String getMODE_OF_PAYMENT() {
		return MODE_OF_PAYMENT;
	}

	public void setMODE_OF_PAYMENT(String mODE_OF_PAYMENT) {
		MODE_OF_PAYMENT = mODE_OF_PAYMENT;
	}

	public String getPLACE_OF_APPLICATION() {
		return PLACE_OF_APPLICATION;
	}

	public void setPLACE_OF_APPLICATION(String pLACE_OF_APPLICATION) {
		PLACE_OF_APPLICATION = pLACE_OF_APPLICATION;
	}

	public String getTRAN_NUMBER() {
		return TRAN_NUMBER;
	}

	public void setTRAN_NUMBER(String tRAN_NUMBER) {
		TRAN_NUMBER = tRAN_NUMBER;
	}

	public String getSUPERVISORID() {
		return SUPERVISORID;
	}

	public void setSUPERVISORID(String sUPERVISORID) {
		SUPERVISORID = sUPERVISORID;
	}

	public String getSUPDATETIME() {
		return SUPDATETIME;
	}

	public void setSUPDATETIME(String sUPDATETIME) {
		SUPDATETIME = sUPDATETIME;
	}

	public String getJOURNAL_NUMBER() {
		return JOURNAL_NUMBER;
	}

	public void setJOURNAL_NUMBER(String jOURNAL_NUMBER) {
		JOURNAL_NUMBER = jOURNAL_NUMBER;
	}

	public String getCBS_ERROR_CODE() {
		return CBS_ERROR_CODE;
	}

	public void setCBS_ERROR_CODE(String cBS_ERROR_CODE) {
		CBS_ERROR_CODE = cBS_ERROR_CODE;
	}

	public String getCBS_ERROR_DESCRIPTION() {
		return CBS_ERROR_DESCRIPTION;
	}

	public void setCBS_ERROR_DESCRIPTION(String cBS_ERROR_DESCRIPTION) {
		CBS_ERROR_DESCRIPTION = cBS_ERROR_DESCRIPTION;
	}

	public String getACCT_HOME_BRANCH() {
		return ACCT_HOME_BRANCH;
	}

	public void setACCT_HOME_BRANCH(String aCCT_HOME_BRANCH) {
		ACCT_HOME_BRANCH = aCCT_HOME_BRANCH;
	}

	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}

	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}

	public String getPREMIUM_AMOUNT() {
		return PREMIUM_AMOUNT;
	}

	public void setPREMIUM_AMOUNT(String pREMIUM_AMOUNT) {
		PREMIUM_AMOUNT = pREMIUM_AMOUNT;
	}

	public String getCIF_CODE() {
		return CIF_CODE;
	}

	public void setCIF_CODE(String cIF_CODE) {
		CIF_CODE = cIF_CODE;
	}

	public String getOVERDUEAMOUNT() {
		return OVERDUEAMOUNT;
	}

	public void setOVERDUEAMOUNT(String oVERDUEAMOUNT) {
		OVERDUEAMOUNT = oVERDUEAMOUNT;
	}

	public String getLANDMARK() {
		return LANDMARK;
	}

	public void setLANDMARK(String lANDMARK) {
		LANDMARK = lANDMARK;
	}

	public String getSI_NEXT_DATE() {
		return SI_NEXT_DATE;
	}

	public void setSI_NEXT_DATE(String sI_NEXT_DATE) {
		SI_NEXT_DATE = sI_NEXT_DATE;
	}

	public String getSI_END_DATE() {
		return SI_END_DATE;
	}

	public void setSI_END_DATE(String sI_END_DATE) {
		SI_END_DATE = sI_END_DATE;
	}

	public String getPENALITY_CHARGE() {
		return PENALITY_CHARGE;
	}

	public void setPENALITY_CHARGE(String pENALITY_CHARGE) {
		PENALITY_CHARGE = pENALITY_CHARGE;
	}

	public String getPENALITY_INST_COUNT() {
		return PENALITY_INST_COUNT;
	}

	public void setPENALITY_INST_COUNT(String pENALITY_INST_COUNT) {
		PENALITY_INST_COUNT = pENALITY_INST_COUNT;
	}

	public String getNO_OF_INST_RECD() {
		return NO_OF_INST_RECD;
	}

	public void setNO_OF_INST_RECD(String nO_OF_INST_RECD) {
		NO_OF_INST_RECD = nO_OF_INST_RECD;
	}

	public String getCOMMISSION_AMT() {
		return COMMISSION_AMT;
	}

	public void setCOMMISSION_AMT(String cOMMISSION_AMT) {
		COMMISSION_AMT = cOMMISSION_AMT;
	}

	public String getPAID_UPTO_DATE() {
		return PAID_UPTO_DATE;
	}

	public void setPAID_UPTO_DATE(String pAID_UPTO_DATE) {
		PAID_UPTO_DATE = pAID_UPTO_DATE;
	}

	public String getCURR_STATUS() {
		return CURR_STATUS;
	}

	public void setCURR_STATUS(String cURR_STATUS) {
		CURR_STATUS = cURR_STATUS;
	}

	public String getOutputType() {
		return outputType;
	}

	public void setOutputType(String outputType) {
		this.outputType = outputType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getRequestString() {
		return requestString;
	}

	public void setRequestString(String requestString) {
		this.requestString = requestString;
	}

	public String getResponseString() {
		return responseString;
	}

	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}

	public String getNO_OF_INST_PENDING() {
		return NO_OF_INST_PENDING;
	}

	public void setNO_OF_INST_PENDING(String nO_OF_INST_PENDING) {
		NO_OF_INST_PENDING = nO_OF_INST_PENDING;
	}

	public String getCONTRIBUTION_MONTH() {
		return CONTRIBUTION_MONTH;
	}

	public void setCONTRIBUTION_MONTH(String cONTRIBUTION_MONTH) {
		CONTRIBUTION_MONTH = cONTRIBUTION_MONTH;
	}

	public String getCONTRIBUTION_YEAR() {
		return CONTRIBUTION_YEAR;
	}

	public void setCONTRIBUTION_YEAR(String cONTRIBUTION_YEAR) {
		CONTRIBUTION_YEAR = cONTRIBUTION_YEAR;
	}

	@Override
	public String toString() {

		logger.debug("CUSTOMER_ACC_NUMBER ====>" + this.CUSTOMER_ACC_NUMBER);
//		logger.debug("BRANCH_CODE ====>"+this.BRANCH_CODE);
		logger.debug("CONTRIBUTION_AMT ====>" + this.CONTRIBUTION_AMT);
//		logger.debug("TELLER_ID ====>"+this.TELLER_ID);

		return super.toString();
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}



}
