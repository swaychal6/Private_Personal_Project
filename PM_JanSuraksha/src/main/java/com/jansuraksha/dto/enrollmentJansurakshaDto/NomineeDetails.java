package com.jansuraksha.dto.enrollmentJansurakshaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NomineeDetails {

	private String nomineeName;
	private String dob;
	private String mobile;
	private String relationShip;
	private String emailId;
	private String addressOfNominee;

}
