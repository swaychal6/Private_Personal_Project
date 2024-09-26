package com.jansuraksha.dto.updatetranJansurakshaDto;

import com.jansuraksha.dto.enrollmentJansurakshaDto.Coi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTransactionDetailsResponse {

	private String message;
	private int status;
	private boolean success;
	private String timestamp;
	private String token;
	private Coi coi;

}
