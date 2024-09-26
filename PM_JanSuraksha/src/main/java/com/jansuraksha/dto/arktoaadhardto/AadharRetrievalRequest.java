package com.jansuraksha.dto.arktoaadhardto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AadharRetrievalRequest {

	private String requestType;
	private String requestChannel;
	private String aadhaarReferenceNumber;
	
}
