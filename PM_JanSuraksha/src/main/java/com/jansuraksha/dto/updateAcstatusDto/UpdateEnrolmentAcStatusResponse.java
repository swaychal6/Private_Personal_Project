package com.jansuraksha.dto.updateAcstatusDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEnrolmentAcStatusResponse {

	private String message;
	private int status;
	private boolean success;
	private String timestamp;
	private String token;

}
