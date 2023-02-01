package com.payhada.admin.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ErrorResponse {
	private String errorCode;
	private String errorMsg;
	private String status;

	@Builder
	public ErrorResponse(String errorCode, String errorMsg, String status) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.status = status;
	}
}
