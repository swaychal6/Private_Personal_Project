package com.jansuraksha.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter	
public enum ResponseCode {

	SUCCESSFUL("PM001", "Successful.", "Successful."),
	INVALID_BANK_CODE("PM002","BANK CODE is Mandatory","BANK CODE is Mandatory"),
	INVALID_INSURANCE_PROVIDER("PM003","INSURANE PROVIDE is Mandatory","INSURANE PROVIDE is Mandatory"),
	INVALID_REQ("PM004", "Invalid request.", "Invalid request."),
	NO_DATA_FOUND("PM005","NO DATA FOUND FOR THE REQUEST!! KINDLY CHECK THE REQUEST AGAIN","NO DATA FOUND FOR THE REQUEST!! KINDLY CHECK THE REQUEST AGAIN"),
	INVALID_PREMIUM_AMOUNT("PM006","PREMIUM AMOUNT is Not Matching With the Quarterly Basis ProRata.","PREMIUM AMOUNT is Not Matching With the Quarterly Basis ProRata."),
	INVALID_AADHAR_NUMER("PM007","AADHAR NUMBER IS INVALID","AADHAR NUMBER IS INVALID"),
	CIF_ALREADY_EXIST("PM008","Customer is already Exist","Customer is already Exist"),
//	INVALID_GE
	;
	private final String code;
	private final String errorMessage;
	private final String userMessage;
}
