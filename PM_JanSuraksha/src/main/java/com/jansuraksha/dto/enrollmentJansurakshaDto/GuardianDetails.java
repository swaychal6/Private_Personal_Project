package com.jansuraksha.dto.enrollmentJansurakshaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardianDetails {
	private String guradianName;
	private String addressofGuardian;
	private String relationShip;
	private String mobile;
	private String emailId;
}
