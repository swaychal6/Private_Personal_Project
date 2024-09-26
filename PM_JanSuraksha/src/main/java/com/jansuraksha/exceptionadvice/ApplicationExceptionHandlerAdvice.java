package com.jansuraksha.exceptionadvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jansuraksha.dto.APIresponse.PM_Response;

@RestControllerAdvice
public class ApplicationExceptionHandlerAdvice {

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> MethodArgumentException(CustomIllegalArgumentsException e) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String errMessage = e.getMessage() == null
				? "No data found for the request, Kindly check the request again!!!"
				: e.getMessage();
//		String userMessage = e.getUserMessage() == null ? Defaults.DEF_USER_ERR_MSG : e.getUserMessage();
		String userMessage = e.getUserMessage() == null ? errMessage : e.getUserMessage();

		PM_Response apiResponse = PM_Response.builder().status(status).responseCode(e.getMessageCode())
				.errorMessage(errMessage).userMessage(userMessage).build();
		return new ResponseEntity<Object>(apiResponse,HttpStatus.BAD_REQUEST);
	}

//	@JsonIgnore
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public /*Map<String , String >*/ ResponseEntity<Object> MethodArgumentException(MethodArgumentNotValidException e) {
		String errMessage=e.getMessage()==null ? "Request Data is not valid!!!" : e.getBindingResult().getFieldError().getDefaultMessage();
				
	 	Map<String, String> errorMap = new HashMap<>();
		e.getBindingResult().getFieldErrors()
				.forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
//		
		PM_Response apiResponse=PM_Response.builder().status(HttpStatus.BAD_REQUEST).responseCode("404")
				.userMessage(e.getBindingResult().getFieldError().getDefaultMessage())
				.errorMessage(errMessage).responseData(e.getBindingResult().getFieldError().getDefaultMessage()).build();
		
//		return errorMap;
		return new ResponseEntity<Object>(apiResponse,new HttpHeaders(),HttpStatus.BAD_REQUEST);
		
	}
	
//	@ExceptionHandler(CustomIllegalArgumentsException.class)
//	public ResponseEntity<Object> handleCustomIllegalArgumentsException(CustomIllegalArgumentsException e) {
//		HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
//		String errMessage = e.getMessage() == null
//				? "Illegal argument exception, please check your request parameters and body"
//				: e.getMessage();
////		String userMessage = e.getUserMessage() == null ? Defaults.DEF_USER_ERR_MSG : e.getUserMessage();
//		String userMessage = e.getUserMessage() == null ? errMessage : e.getUserMessage();
//
//		PM_Response apiResponse = PM_Response.builder().status(status).responseCode(e.getMessageCode())
//				.errorMessage(errMessage).userMessage(userMessage).build();
//		return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), status);
//	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Object> contraintViolationException(SQLIntegrityConstraintViolationException e){
		HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
		String errMessage = e.getMessage() == null
				? "Data is invalid"
				: e.getMessage();
//		String userMessage = e.getUserMessage() == null ? Defaults.DEF_USER_ERR_MSG : e.getUserMessage();
		String userMessage = e.getMessage() == null ? errMessage : e.getMessage();

		PM_Response apiResponse = PM_Response.builder().status(status).responseCode(String.valueOf(e.getErrorCode()))
				.errorMessage(errMessage).userMessage(userMessage).build();
		return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), status);
	}
}
