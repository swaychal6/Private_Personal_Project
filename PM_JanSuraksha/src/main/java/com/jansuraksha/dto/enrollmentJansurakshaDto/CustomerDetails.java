package com.jansuraksha.dto.enrollmentJansurakshaDto;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDetails {

//	@NotNull(message = "Account Number Is Mandatory")
//	@Size(max = 17, min = 3, message = "Account Number Is Incorrect")
//	private String accountNumber;
//
//	@NotNull(message = "CIF Number Is Mandatory")
//	@Size(max = 17, min = 3, message = "CIF Number Is Incorrect")
//	private String cif;
//
//	@NotNull(message = "Customer IFSC Is Mandatory")
//	@Size(max = 300, min = 1, message = "Customer IFSC Is too Long or short")
//	@Pattern(regexp = "^[a-zA-Z]+[a-zA-Z .'/-]*[a-zA-Z.'/-]*$", message = "Customer IFSC is Invalid")
//	private String customerIFSC;
//
//	@NotNull(message = "Customer IFSC Is Mandatory")
//	@Size(max = 150, min = 1, message = "Account Holder name is too Long or short")
//	private String accountHolderName;
//
//	@NotNull(message = "Gender is Mandatory")
//	@Pattern(regexp = "M|F|T", message = "Gender should be 'M' for Male ,'F' for Female and 'T' for Transgender")
//	private String gender;

	private String accountNumber;
	private String cif;
	private String customerIFSC;
	private String accountHolderName;
	private String gender;
	private String fatherHusbandName;
	
	@JsonFormat(pattern = " yyyy-MM-dd")
	private String dob;
	private String mobileNumber;
	private String emailId;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String district;
	private String state;
	private String pincode;
	private String disabilityStatus;
	private String disabilityDetails;
	private String applicantOccupation;
}
