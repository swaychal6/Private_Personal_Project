package com.jansuraksha.dto.enrollmentJansurakshaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentRequestDto {

	// token should be txn-id of the customer with the unqiuness
	private String token;

	private CustomerDetails customerDetails;

	private KycDetails kycDetails;

	private NomineeDetails nomineeDetails;

	private GuardianDetails guardianDetails;

	private OtherDetails otherDetails;
}
