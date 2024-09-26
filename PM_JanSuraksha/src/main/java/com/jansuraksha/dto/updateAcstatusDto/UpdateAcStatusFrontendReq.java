package com.jansuraksha.dto.updateAcstatusDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAcStatusFrontendReq {
	
	private String customerAcc;
	
	private String cifCode;
	
	private String scheme;

	private String accountStatus;

}
