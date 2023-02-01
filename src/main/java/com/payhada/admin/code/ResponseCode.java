package com.payhada.admin.code;

import com.payhada.admin.common.setting.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Map;

@Getter
@AllArgsConstructor
public enum ResponseCode {
	/** 공통 코드 */
	API_STATUS_OK("E0000", 200),

	API_BAD_REQUEST("E1001",400),
	API_DUP_REQUEST("E0019",400),
	API_FAIL_SELECT("E1010",500),
	API_FAIL_INSERT("E1021",500),
	API_FAIL_UPDATE("E1031",500),
	API_FAIL_DELETE("E1041",500),
	API_PROCESS_ERROR("E1998", 500),
	API_SERVER_ERROR("E9999",500),

	/** 유저/직원 관련 오류 코드 */
	USER_SESSION_EXPIRED("E2000", 401),
	USER_NOT_FOUND("E2001", 404),
	USER_UNAUTHORIZED("E2002", 401),
	USER_AUTH_REQUIRED("E2003", 403),
	SUCCESSFUL_LOGIN_1("E2004", 200),
	SUCCESSFUL_LOGIN_2("E2005", 200),
	MISMATCH_PASSWORD("E2006", 400),
	MISMATCH_OTP("E2007", 400),
	UNAUTHENTICATED_1("E2008", 401),
	TIMEOUT_OTP("E2009", 400),
	LOCKED_ACCOUNT("E2010", 500),
	SUCCESSFUL_LOGOUT("E2011", 200),

	/** NCP 관련 오류 코드 */
	NCP_MAKE_SIGNATURE_ERR("E3000", 500),
	NCP_GENERATE_HEADER_ERR("E3001", 500),
	NCP_FAIL_MAIL_SERVICE("E3002", 500),
	;

	private final String code;

	private final int status;

	public ResponseEntity<Response> toResponseEntity() {
		return ResponseEntity
				.status(this.getStatus())
				.body(Response.create(this.getCode()));
	}

	public ResponseEntity<Response> toResponseEntity(HttpHeaders headers) {
		return ResponseEntity
				.status(this.getStatus())
				.headers(headers)
				.body(Response.create(this.getCode()));
	}

	public ResponseEntity<Response> toResponseEntity(Map<String, Object> data) {
		return ResponseEntity
				.status(this.getStatus())
				.body(Response.create(this.getCode(), data));
	}

	public ResponseEntity<Response> toResponseEntity(HttpHeaders headers, Map<String, Object> data) {
		return ResponseEntity
				.status(this.getStatus())
				.headers(headers)
				.body(Response.create(this.getCode(), data));
	}
}
