package com.apy_si.CBSUtil;

public class Rural_GetterSetter {

	private String strRequest = new String();
	private String strResponse = new String();
	private String strPolicyNum = new String();
	private String strCBSRefNum = new String();
	private String strCompanyName = new String();
	private String strCBSNarration = new String();
	private String strCompanyAccount = new String();
	private String strReferenceNumber = new String();
	private String strCBSIP = new String();
	private Integer strCBSPort;
	// added by basava
	private String COMPANY_ACC_NUMBER;
	private String PREMIUM_AMOUNT;
	private String BRANCH_CODE;
	private String TELLER_ID;
	private String CUSTOMER_ACC_NUMBER;
	private String NARRATIVE;
	private String TRAN_NUMBER;
	private String SupervisorID;
	private String SupDateTime;

	private int iBankCode = 0;

	public Rural_GetterSetter() {
		strRequest = "";
		strCBSRefNum = "";
		setStrPolicyNum("");
		strCompanyName = "";
		strCBSNarration = "";
		strCompanyAccount = "";
		strReferenceNumber = "";
		strCBSIP = "";
		strCBSPort = 0;
	}

	public String getStrCBSIP() {
		return strCBSIP;
	}

	public void setStrCBSIP(String strCBSIP) {
		this.strCBSIP = strCBSIP;
	}

	public Integer getStrCBSPort() {
		return strCBSPort;
	}

	public void setStrCBSPort(Integer strCBSPort) {
		this.strCBSPort = strCBSPort;
	}

	public String getStrCBSNarration() {
		return strCBSNarration;
	}

	public void setStrCBSNarration(String strCBSNarration) {
		this.strCBSNarration = strCBSNarration;
	}

	public String getStrCBSRefNum() {
		return strCBSRefNum;
	}

	public void setStrCBSRefNum(String strCBSRefNum) {
		this.strCBSRefNum = strCBSRefNum;
	}

	public String getStrRequest() {
		return strRequest;
	}

	public void setStrRequest(String strRequest) {
		this.strRequest = strRequest;
	}

	public String getStrResponse() {
		return strResponse;
	}

	public void setStrResponse(String strResponse) {
		this.strResponse = strResponse;
	}

	public void setiBankCode(int iBankCode) {
		this.iBankCode = iBankCode;
	}

	public int getiBankCode() {
		return iBankCode;
	}

	public String getCOMPANY_ACC_NUMBER() {
		return COMPANY_ACC_NUMBER;
	}

	public void setCOMPANY_ACC_NUMBER(String cOMPANYACCNUMBER) {
		COMPANY_ACC_NUMBER = cOMPANYACCNUMBER;
	}

	public String getPREMIUM_AMOUNT() {
		return PREMIUM_AMOUNT;
	}

	public void setPREMIUM_AMOUNT(String pREMIUMAMOUNT) {
		PREMIUM_AMOUNT = pREMIUMAMOUNT;
	}

	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}

	public void setBRANCH_CODE(String bRANCHCODE) {
		BRANCH_CODE = bRANCHCODE;
	}

	public String getTELLER_ID() {
		return TELLER_ID;
	}

	public void setTELLER_ID(String tELLERID) {
		TELLER_ID = tELLERID;
	}

	public String getCUSTOMER_ACC_NUMBER() {
		return CUSTOMER_ACC_NUMBER;
	}

	public void setCUSTOMER_ACC_NUMBER(String cUSTOMERACCNUMBER) {
		CUSTOMER_ACC_NUMBER = cUSTOMERACCNUMBER;
	}

	public String getNARRATIVE() {
		return NARRATIVE;
	}

	public void setNARRATIVE(String nARRATIVE) {
		NARRATIVE = nARRATIVE;
	}

	public String getTRAN_NUMBER() {
		return TRAN_NUMBER;
	}

	public void setTRAN_NUMBER(String tRANNUMBER) {
		TRAN_NUMBER = tRANNUMBER;
	}

	public String getSupervisorID() {
		return SupervisorID;
	}

	public void setSupervisorID(String supervisorID) {
		SupervisorID = supervisorID;
	}

	public String getSupDateTime() {
		return SupDateTime;
	}

	public void setSupDateTime(String supDateTime) {
		SupDateTime = supDateTime;
	}

	public void setStrCompanyAccount(String strCompanyAccount) {
		this.strCompanyAccount = strCompanyAccount;
	}

	public String getStrCompanyAccount() {
		return strCompanyAccount;
	}

	public void setStrReferenceNumber(String strReferenceNumber) {
		this.strReferenceNumber = strReferenceNumber;
	}

	public String getStrReferenceNumber() {
		return strReferenceNumber;
	}

	public void setStrCompanyName(String strCompanyName) {
		this.strCompanyName = strCompanyName;
	}

	public String getStrCompanyName() {
		return strCompanyName;
	}

	public void setStrPolicyNum(String strPolicyNum) {
		this.strPolicyNum = strPolicyNum;
	}

	public String getStrPolicyNum() {
		return strPolicyNum;
	}

}
