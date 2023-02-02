package com.payhada.admin.exception;

import com.payhada.admin.code.ResponseCode;

import java.util.Map;

public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;

	private final ResponseCode responseCode;

	private final Map<String, Object> data;

	public BusinessException(ResponseCode code) {
		this.responseCode = code;
		this.data = null;
	}

	public BusinessException(ResponseCode responseCode, Map<String, Object> data) {
		this.responseCode = responseCode;
		this.data = data;
	}

	public ResponseCode getResponseCode() {
		return this.responseCode;
	}

	public Map<String, Object> getData() {
		return this.data;
	}
}
