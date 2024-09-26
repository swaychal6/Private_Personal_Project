package com.jansuraksha.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public enum EnrollmentResponseEnum {

	SUCCESS(200,"SUCCESS"),
	ALREADY_EXIST(208,"Already Reported(Customer is already enrolled with Scheme or Enrollment in process with other bank."),
	INTERNAL_SERVER_ERROR(500,"Internal Server Error"),
	BAD_REQUEST(400,"Parameter Missing in Request (Bad Request)"),
	INVALID_REQUEST(400,"Invalid Application Reference Id (Bad Request)"),
	REATTEMPT(999,"REATTEMPT")
	;
	
 /*	JanSuraksha Enrollment success code and messages
	200 - Success
	208 - Already Reported(Customer is already enrolled with Scheme or Enrollment in process with other bank.)
	500 - Internal Server Error
	400 - Parameter Missing in Request (Bad Request)
	400 - Invalid Application Reference Id (Bad Request)
 */
	
	
	private final int id;
	private final String response;
	
	EnrollmentResponseEnum(int id, String response){
		this.id=id;
		this.response=response;
	}
	
	  public static String getMessageByCode(int code) {
	        for (EnrollmentResponseEnum status : EnrollmentResponseEnum.values()) {
	            if (status.getId() == code) {
	                return status.getResponse();
	            }
	        }
	        return "Unknown status code";
	    }
}
