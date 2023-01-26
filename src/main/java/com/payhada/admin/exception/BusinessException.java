package com.payhada.admin.exception;

import com.payhada.admin.code.ErrorCode;

public class BusinessException extends Exception{
	private static final long serialVersionUID = 1L;

	private final ErrorCode errorCode;

	public BusinessException(ErrorCode code) {
		this.errorCode = code;
	}

	public ErrorCode getError() {
		return errorCode;		
	}
}
