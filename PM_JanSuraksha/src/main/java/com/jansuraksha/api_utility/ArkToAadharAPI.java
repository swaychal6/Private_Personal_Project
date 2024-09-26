package com.jansuraksha.api_utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.jansuraksha.config.ApplicationConfig;
import com.jansuraksha.dto.arktoaadhardto.AadharRetrievalRequest;
import com.jansuraksha.dto.arktoaadhardto.AadharRetrievalResponse;

@Component
public class ArkToAadharAPI {

	private static final Logger logger=LogManager.getLogger(ArkToAadharAPI.class);

	private final WebClient webClient;
	
	public ArkToAadharAPI(WebClient.Builder builder){
		this.webClient=builder.build();
	}
	
	public AadharRetrievalResponse aadharResponse(String aadhar, String bankCode) {
		AadharRetrievalResponse response=null;
		try {
		AadharRetrievalRequest aadharRetrievalRequest = AadharRetrievalRequest.builder()
		.aadhaarReferenceNumber(aadhar)
		.requestChannel(ApplicationConfig.getProperty("requestChannel"))
		.requestType(ApplicationConfig.getProperty("requestTypeRRBCode"))
		.build();
		
		
		 response = webClient
		.post()
		.uri(ApplicationConfig.getProperty(bankCode))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
		.bodyValue(aadharRetrievalRequest)
		.retrieve()
		.bodyToMono(AadharRetrievalResponse.class)
		.block();
	
	        
		return response;
		
		}catch(Exception e) {
			logger.error("Error occurs while getting response AadharRetrievalResponse: "+e.getMessage());
			response=AadharRetrievalResponse.builder()
			.aadhaar_no("")
			.response_code("404")
			.response_message("Connectivity Issue:").build();
		}
		
		return response;

	}
}
