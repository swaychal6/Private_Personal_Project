package com.jansuraksha.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.jansuraksha.PmJanSurakshaApplication;
//import com.jansuraksha.PmJanSurakshaApplication;
import com.jansuraksha.api_utility.ArkToAadharAPI;
import com.jansuraksha.dto.arktoaadhardto.AadharRetrievalResponse;
import com.jansuraksha.dto.enrollmentJansurakshaDto.CustomerDetails;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentRequestDto;
import com.jansuraksha.dto.enrollmentJansurakshaDto.GuardianDetails;
import com.jansuraksha.dto.enrollmentJansurakshaDto.KycDetails;
import com.jansuraksha.dto.enrollmentJansurakshaDto.NomineeDetails;
import com.jansuraksha.dto.enrollmentJansurakshaDto.OtherDetails;
import com.jansuraksha.utils.CommonUtility;


@Component
public class CommonRowMapper implements RowMapper<EnrollmentRequestDto> {

//	@Autowired
//	private ArkToAadharAPI aadharAPI;
	
//	private String aadhar=null;

	@Override
	public EnrollmentRequestDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		GuardianDetails guardianDetails=null;
		CustomerDetails customerDetails=this.customerDetails(rs, rowNum);
		
		KycDetails kycDetails = this.kycDetailsMapper(rs,rowNum);
		
		NomineeDetails nomineeDetails= this.nomineeDetailsMapper(rs,rowNum);
		
		long nomineeAge = CommonUtility.calculateAge(nomineeDetails.getDob());
		
		if(!(nomineeAge<18)) {
			guardianDetails=this.guardianDetailsMapper(rs,rowNum);
		}
		
		OtherDetails otherDetails= this.otherDetailsMapper(rs,rowNum);
		
		String checkChannelId = this.checkChannelId(rs.getString("SOURCE"));
		
		String token=this.generateToken(rs.getString("BANK_CODE"),checkChannelId);
		
		return  EnrollmentRequestDto.builder()
		.customerDetails(customerDetails)
		.kycDetails(kycDetails)
		.nomineeDetails(nomineeDetails)
		.guardianDetails(guardianDetails)
		.otherDetails(otherDetails)
		.token(token)
		.build();
		
	}

	private String checkChannelId(String channelId) {
		if(channelId.equals("JAVA")|| channelId.equals("DOTNET")) {
			return "WEBSITE";
		}
		
		if(channelId.equals("BC"))
			return "BC";
		
		return channelId;
	}

	/*
	private String callArk(String aadhar,String bankCode) {
		
		AadharRetrievalResponse aadharResponse = aadharAPI.aadharResponse(aadhar, bankCode);
		
		if(aadharResponse.getResponse_code().equals("89")) {
			this.aadhar=aadharResponse.getAadhaar_no();
			return  aadharResponse.getAadhaar_no(); 
		}else {
			this.aadhar=null;
		}
		
		return aadhar;
		
	}
   */
	
	private CustomerDetails customerDetails(ResultSet rs, int rowNum) throws SQLException {
			return CustomerDetails.builder()
					.accountHolderName(rs.getString("ACCT_HOLDER_NAME"))
					.addressLine1(rs.getString("ADD1"))
					.addressLine2(rs.getString("ADD2"))
					.accountNumber(rs.getString("CUSTOMER_ACC_NUMBER"))
					.cif(rs.getString("CIF_CODE"))
					.dob(CommonUtility.dobFormatter(rs.getString("NOMINEE_DOB")))
					.customerIFSC(rs.getString("BRANCH_IFSC"))
					.gender(rs.getString("GENDER"))
					.city(rs.getString("CITY"))
					.mobileNumber(rs.getString("MOBILE"))
					.applicantOccupation(rs.getString("MEMBER_OCCUPATION") )
					.district(rs.getString("NAME_OF_DISTRICT"))
					.emailId(rs.getString("EMAILID"))
					.pincode(rs.getString("PINCODE"))
					.state(rs.getString("STATE"))
					.build();
			 
	}
	
	private KycDetails kycDetailsMapper(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
//		String aadharDetail=this.callArk(rs.getString("AADHAR"),rs.getString("BANK_CODE"));
		
		return KycDetails.builder()
		.kycId1("AADHAR")
		.kycIdValue1("123456789000")
		.aadhaarNumber("123456789000")
		.panNumber("")
		.build();
		
	}
	
	private NomineeDetails nomineeDetailsMapper(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return NomineeDetails.builder()
				.dob(CommonUtility.dobFormatter(rs.getString("NOMINEE_DOB")))
				.emailId(rs.getString("NOMINEE_EMAIL"))
				.mobile(rs.getString("NOMINEE_MOBILE_NUMBER"))
				.nomineeName(rs.getString("NOMINEE_NAME"))
				.addressOfNominee(rs.getString("NOMINEE_ADDRESS"))
				.relationShip(rs.getString("NOMINEE_REL"))
				.build();
	}
	
	private GuardianDetails guardianDetailsMapper(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return GuardianDetails.builder()
				.emailId(rs.getString("APPOINTEE_EMAIL"))
				.relationShip(rs.getString("APPOINTEE_REL_WITH_NOMINEE"))
				.addressofGuardian(rs.getString("APPOINTEE_ADDRESS"))
				.mobile(rs.getString("APPOINTEE_MOBILE_NUMBER"))
				.guradianName(rs.getString("APPOINTEE_NAME"))
				.build();
	}
	
	private OtherDetails otherDetailsMapper(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		String bank=rs.getString("BANK_CODE");
		return OtherDetails.builder()
				.bankCode(PmJanSurakshaApplication
						.bankDetails
						.stream()
						.filter(t-> bank.equalsIgnoreCase(t.getBANK_CODE()))
						.map(t ->t.getBANK_NAME()).findFirst().get())
				.branchCode(rs.getString("BRANCH_CODE"))
				.consentForAutoDebit("Yes")
				.channelId(this.checkScheme(rs.getString("COMPANY_NAME")))
				.userId1(rs.getString("TELLER_ID"))
				.build();
				
				
	}
	
	private String checkScheme(String companyName) throws SQLException {
		if("SBI".equals(companyName) || "LIC".equals(companyName)) {
			return "PMJJBY";
		}
		return "PMSBY";
	}

	//
	private String generateToken(String bankCode,String source) {

		String jansurakshaToken = CommonUtility.generateJansurakshaToken(bankCode,source);
		
		return jansurakshaToken;
	}
}
