package com.jansuraksha.dto.enrollmentJansurakshaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentDetailsResponse {

	private String urn;
	private String message;
	private int status;
	private boolean success;
	private String timestamp;
	private String token;

}
