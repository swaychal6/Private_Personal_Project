package com.jansuraksha.dto.updateAcstatusDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTransactionAcStatusRequest {
	private String accountNumber;
	private String dob;
	private String cif;
	private String urn;
	private String accountStatus;
	private String reason;
	private String token;

}
