package com.payhada.admin.exception;

import com.payhada.admin.code.ErrorCode;

public class BusinessException extends Exception{
	private static final long serialVersionUID = 1L;

	private final ErrorCode errorCode;

	public BusinessException(ErrorCode code) throws Exception {
		this.errorCode = code;
		
		try {
			
		} catch (Exception e) {
			throw new ApplicationException(code);
		}
	}

	public ErrorCode getError() {
		return errorCode;		
	}
}
