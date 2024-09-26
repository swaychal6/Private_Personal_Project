package com.jansuraksha.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jansuraksha.PmJanSurakshaApplication;
import com.jansuraksha.api_utility.JanSurakshaAPICommonUtility;
import com.jansuraksha.config.ApplicationConfig;
import com.jansuraksha.daoImpl.CommonRepoDaoImpl;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentDetailsResponse;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentRequestDto;
import com.jansuraksha.dto.updatetranJansurakshaDto.UpdateTransactionDetailsRequest;
import com.jansuraksha.dto.updatetranJansurakshaDto.UpdateTransactionDetailsResponse;
import com.jansuraksha.entity.JansurakshaTransactions;
import com.jansuraksha.utils.CommonUtility;

@Component
public class JanSurakshaServices<T> extends CommonRepoDaoImpl<T> {

	private static final Logger logger = LogManager.getLogger(JanSurakshaServices.class);

	private final JanSurakshaAPICommonUtility apiCallingUtility;

	public JanSurakshaServices(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
		this.apiCallingUtility = null;
	}

	protected JansurakshaTransactions callEnrollmentAndUpdateDetails(EnrollmentRequestDto dto, String bankCode) {
		JansurakshaTransactions mappedToJansurakshaTransactions = null;
		UpdateTransactionDetailsResponse updateJansurakshaTransactionDetailsResponse=null;
		try {
			EnrollmentDetailsResponse enrollmentResponseJNS = apiCallingUtility.enrollmentDetailsJansuraskhaAPI(dto);

			mappedToJansurakshaTransactions = super.mappedToJansurakshaTransactions(enrollmentResponseJNS.getUrn(),
					dto.getCustomerDetails().getAccountNumber(), dto.getCustomerDetails().getCif(), bankCode, null);

			boolean updateResponse = this.updateResponse(mappedToJansurakshaTransactions);

			if (updateResponse) {
				UpdateTransactionDetailsRequest mappedUpdateTransactionDetailsRequest = this
						.mappedUpdateTransactionDetailsRequest(dto,enrollmentResponseJNS.getUrn(),bankCode);
				
				 updateJansurakshaTransactionDetailsResponse = apiCallingUtility
						.updateJansurakshaTransactionDetails(mappedUpdateTransactionDetailsRequest);
				 
				if(null!=updateJansurakshaTransactionDetailsResponse.getCoi() && 200==updateJansurakshaTransactionDetailsResponse.getStatus()) {
					mappedToJansurakshaTransactions.setTimestamp(updateJansurakshaTransactionDetailsResponse.getTimestamp());
					mappedToJansurakshaTransactions.setCoi_document_code(updateJansurakshaTransactionDetailsResponse.getCoi().getDocument());
					mappedToJansurakshaTransactions.setTransaction_status(updateJansurakshaTransactionDetailsResponse.getMessage());
				
				int saveJansurakshaDetails = super.saveJansurakshaDetails(mappedToJansurakshaTransactions);
				if (saveJansurakshaDetails > 0) {
					logger.info(saveJansurakshaDetails + ": row inserted in the jansuraksha transaction table "+mappedToJansurakshaTransactions.toString());
				}
					
				}
				
				if(null==updateJansurakshaTransactionDetailsResponse.getCoi() || 999==updateJansurakshaTransactionDetailsResponse.getStatus()) {
					mappedToJansurakshaTransactions.setStatus(999);
					this.updateResponse(mappedToJansurakshaTransactions);
				}

			}

		} catch (Exception e) {
			logger.error("");
		}

		return mappedToJansurakshaTransactions;
	}

	
	protected boolean updateResponse(JansurakshaTransactions jansurakshaTransactions) {

		boolean successTransaction = false;

		if (null!=jansurakshaTransactions && 200 == jansurakshaTransactions.getStatus()) {
			int updateTransactions = super.updateJansurakshaUrnTransactions(jansurakshaTransactions);

			if (updateTransactions > 0) {
				logger.info(updateTransactions + ": row Updated ");
				successTransaction = true;
			}
		}

		if (999 == jansurakshaTransactions.getStatus()) {
			int updateTransactions = super.updateJansurakshaUrnTransactions(jansurakshaTransactions);

			if (updateTransactions > 0) {
				logger.info(updateTransactions + ": row Updated for Re-Attempt");
			}
		}
		
		return successTransaction;

	}

	protected UpdateTransactionDetailsRequest mappedUpdateTransactionDetailsRequest(EnrollmentRequestDto dto,String urn,String bankCode) {

		return UpdateTransactionDetailsRequest.builder().debitStatus(1)
				.insurerCode(this.getInsurarCode(dto.getOtherDetails().getChannelId(),
						dto.getOtherDetails().getBankCode()))
				.masterPolicyNumber(this.masterPolicyNumber(dto.getOtherDetails().getChannelId(), bankCode))
				.token(CommonUtility.generateJansurakshaToken(bankCode,dto.getOtherDetails().getChannelId()))
				.transactionAmount(CommonUtility.getPREMIUM_AMOUNT(dto.getOtherDetails().getChannelId()))
				.urn(urn)
				.transactionType("1") //transaction type 1 is for new enrollment
				.build();
	}

	private String getInsurarCode(String channelId,String bankCode) {
		// TODO Auto-generated method stub
		if(channelId.equals("PMJJBY")) {
			
		return PmJanSurakshaApplication.bankDetails
			.stream()
			.filter(t -> bankCode.equals(t.getBANK_CODE()))
			.map(t -> getInsurarCodeByScheme( t.getPMJJBY().substring(0,3)))
			.findFirst().get().toString();

		}else {

		return	PmJanSurakshaApplication.bankDetails
			.stream()
			.filter(t -> bankCode.equals(t.getBANK_CODE()))
			.map(t -> getInsurarCodeByScheme( t.getPMSBY().substring(0,3)))
			.findFirst().get().toString();

		}
		
		
	}	

	private  int getInsurarCodeByScheme(String insurar) {

		switch (insurar) {
		case "SBI":

			return 111;

		case "LIC":
			return 512;

		case "NIC":
			return 58;

		case "NIA":
			return 190;

		case "UNI":
			return 545;

		default:
			break;
		}
		return 0;

	}

	
	private String masterPolicyNumber(String scheme,String bankCode) {
			    
			if(scheme.equals("PMJJBY")) {
				return ApplicationConfig.getProperty("PMJJBY_MP"+bankCode);
			}else {
				return ApplicationConfig.getProperty("PMSBY_MP"+bankCode);
			}
	}
	
			

	
	

}
