package com.jansuraksha.dto.updatetranJansurakshaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTransactionDetailsRequest {

	private String urn;
	private int debitStatus;
	private String transactionTimeStamp;
	private double transactionAmount;
	private String masterPolicyNumber;
	private String insurerCode;
	private String transactionUTR;
	private String transactionType;
	private String token;

//	{
//		  "urn": "JNS-PMJJBY-23-24-00000000001-123",
//		  "debitStatus": 1,
//		  "transactionTimeStamp": "2023-05-05 16:12:10",
//		  "transactionAmount": 0,
//		  "masterPolicyNumber": "string",
//		  "insurerCode": "44444",
//		  "transactionUTR": "string",
//		  "transactionType": "string",
//		  "token": "string"
//		}
}
