package com.jansuraksha.dto.arktoaadhardto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AadharRetrievalResponse {

	private String request_date;
	private String bankCode;
	private String requestChannel;
	private String status;
	private String aadhaar_no;
	private String response_code;
	private String response_message;
	
}
