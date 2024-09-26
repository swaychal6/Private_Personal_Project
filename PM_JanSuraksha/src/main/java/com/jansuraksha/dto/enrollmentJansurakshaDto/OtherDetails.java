package com.jansuraksha.dto.enrollmentJansurakshaDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtherDetails {
	private String bankCode;
	private String branchCode;
	private String consentForAutoDebit;
	private String userId1;
	private String userId2;
	private String channelId;
}
