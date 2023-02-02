package com.payhada.admin.code;

import com.payhada.admin.common.setting.CommonResponse;
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
	LOCKED_ACCOUNT("E2010", 401),
	SUCCESSFUL_LOGOUT("E2011", 200),

	/** NCP 관련 오류 코드 */
	NCP_MAKE_SIGNATURE_ERR("E3000", 500),
	NCP_GENERATE_HEADER_ERR("E3001", 500),
	NCP_FAIL_MAIL_SERVICE("E3002", 500),
	;

	private final String code;

	private final int status;

	/**
	 * 선택된 ResponseCode Enum 의 값을 토대로 공통 포맷(CommonResponse) 의 resultCode 와 resultMsg 가 만들어지고
	 * HttpStatusCode 또한 해당 값으로 ResponseEntity 객체가 리턴 됨
	 */
	public ResponseEntity<CommonResponse> toResponseEntity() {
		return ResponseEntity
				.status(this.getStatus())
				.body(CommonResponse.create(this.getCode()));
	}

	/**
	 * {@code toResponseEntity()} 에서 추가로 Header 를 이용할 때 사용
	 */
	public ResponseEntity<CommonResponse> toResponseEntity(HttpHeaders headers) {
		return ResponseEntity
				.status(this.getStatus())
				.headers(headers)
				.body(CommonResponse.create(this.getCode()));
	}

	/**
	 * {@code toResponseEntity()} 에서 추가로 Body 를 이용할 때 사용
	 */
	public ResponseEntity<CommonResponse> toResponseEntity(Map<String, Object> data) {
		return ResponseEntity
				.status(this.getStatus())
				.body(CommonResponse.create(this.getCode(), data));
	}

	/**
	 * {@code toResponseEntity()} 에서 추가로 Body 와 Header 를 이용할 때 사용
	 */
	public ResponseEntity<CommonResponse> toResponseEntity(HttpHeaders headers, Map<String, Object> data) {
		return ResponseEntity
				.status(this.getStatus())
				.headers(headers)
				.body(CommonResponse.create(this.getCode(), data));
	}
}
