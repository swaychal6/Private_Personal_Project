package com.jansuraksha.exceptionadvice;


import com.jansuraksha.enums.ResponseCode;

import lombok.NonNull;

public class CustomIllegalArgumentsException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomIllegalArgumentsException(@NonNull ResponseCode err) {
		super(err);
	}

	public CustomIllegalArgumentsException(@NonNull String err) {
		super(err);
	}

}
