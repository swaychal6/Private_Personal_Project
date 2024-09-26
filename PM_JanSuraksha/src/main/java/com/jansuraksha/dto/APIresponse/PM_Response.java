package com.jansuraksha.dto.APIresponse;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jansuraksha.enums.ResponseCode;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class PM_Response {

	private HttpStatus status;
	String userMessage;
	String responseDataType;
	Object responseData;
	String responseCode;
	String errorMessage;
	String sysErrorMessage;

	@JsonIgnore
	public static PM_Response getEmptySuccessResponse(ResponseCode message) {
		PM_Response apiResponse;
		apiResponse = PM_Response.builder().status(HttpStatus.OK).userMessage(message.getUserMessage()).responseData("")
				.responseDataType("").build();

		return apiResponse;
	}

	@JsonIgnore
	public static PM_Response getEmptySuccessResponse(String message) {
		PM_Response apiResponse;
		apiResponse = PM_Response.builder().status(HttpStatus.OK).userMessage(message).responseData("")
				.responseDataType("").build();

		return apiResponse;
	}

	@JsonIgnore
	public static PM_Response getBasicSuccessResponseObject(@NonNull Object e, ResponseCode message) {

		return PM_Response.builder().status(HttpStatus.OK).userMessage(message.getUserMessage())
				.responseCode(message.getCode()).errorMessage(message.getErrorMessage()).responseData(e)
				.responseDataType(e.getClass().getSimpleName()).build();
	}

	@JsonIgnore
	public static PM_Response getBasicSuccessResponseList(@NonNull List<?> list, ResponseCode message) {

		return PM_Response.builder().status(HttpStatus.OK).userMessage(message.getUserMessage())
				.responseCode(message.getCode()).errorMessage(message.getErrorMessage())
				.responseData(list.stream().toString()).responseDataType(list.getClass().getSimpleName()).build();
	}

	@JsonIgnore
	public static PM_Response getBadRequestFailureResponse(ResponseCode error) {
		String inErrorMessage = "Bad request";
		return PM_Response.builder().status(HttpStatus.BAD_REQUEST).errorMessage(inErrorMessage)
				.userMessage(error.getUserMessage()).sysErrorMessage(error.getErrorMessage())
				.responseCode(error.getCode()).build();
	}

	@JsonIgnore
	public static PM_Response getGenericFailureResponse(String inSysErrorMessage) {
		String inErrorMessage = "Something went wrong";
		PM_Response apiResponse = PM_Response.builder().status(HttpStatus.SERVICE_UNAVAILABLE)
				.errorMessage(inErrorMessage).sysErrorMessage(inSysErrorMessage).build();
		return apiResponse;
	}

}
