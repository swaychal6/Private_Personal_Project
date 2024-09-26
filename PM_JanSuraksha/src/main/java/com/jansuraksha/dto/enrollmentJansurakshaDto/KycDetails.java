package com.jansuraksha.dto.enrollmentJansurakshaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KycDetails {
	
	private String kycId1;
	private String kycIdValue1;
	private String panNumber;
	private String aadhaarNumber;

}
