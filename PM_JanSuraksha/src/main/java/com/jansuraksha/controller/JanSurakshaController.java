package com.jansuraksha.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jansuraksha.dto.APIresponse.PM_Response;
import com.jansuraksha.dto.enrollmentJansurakshaDto.EnrollmentRequestDto;
import com.jansuraksha.services.PmjjbySBIService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JanSurakshaController {
	
	private static final Logger logger=LogManager.getLogger(JanSurakshaController.class);
	
	private final PmjjbySBIService sbiService;

	
	@PostMapping("/enrollment")
	public PM_Response enrollmentDetails(EnrollmentRequestDto enrollmentRequestDto) {
		
		if(!StringUtils.hasText(enrollmentRequestDto.getCustomerDetails().getGender())) {
//			throw new 
		}
		
		return null;
	}
	
	

	
	
	@PostMapping
	public void checkdetails() {
		sbiService.prepareEnrollmentRequestSBI("1859");
	}
}
