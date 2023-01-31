package com.payhada.admin.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
	/** 공통 오류 코드 */
	API_STATUS_OK("E0000", 200),
	API_BAD_REQUEST("E1001",400),
	API_DUP_REQUEST("E0019",400),
	API_FAIL_SELECT("E1010",500),
	API_FAIL_INSERT("E1021",500),
	API_FAIL_UPDATE("E1031",500),
	API_FAIL_DELETE("E1041",500),
	// 여기에 추가
	API_PROCESS_ERROR("E1998", 500),
	API_SERVER_ERROR("E9999",500),

	/** 유저/직원 관련 오류 코드 */
	USER_SESSION_EXPIRED("E2000", 401),
	USER_NOT_FOUND("E2001", 404),
	USER_UNAUTHORIZED("E2002", 401),
	USER_AUTH_REQUIRED("E2003", 403),

	/** NCP 관련 오류 코드 */
	NCP_MAKE_SIGNATURE_ERR("E3000", 500),
	NCP_GENERATE_HEADER_ERR("E3001", 500),
	NCP_FAIL_MAIL_SERVICE("E3002", 500),
	;

	private final String code;

	private final int status;
}
