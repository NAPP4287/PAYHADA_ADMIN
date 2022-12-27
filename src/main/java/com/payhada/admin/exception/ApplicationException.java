package com.payhada.admin.exception;

import com.payhada.admin.code.ErrorCode;

public class ApplicationException extends Exception {
	private static final long serialVersionUID = 1L;

	private final ErrorCode errorCode;

	public ApplicationException(ErrorCode code){
		this.errorCode = code;
	}

	public ErrorCode getError() {
		return errorCode;		
	}
}
