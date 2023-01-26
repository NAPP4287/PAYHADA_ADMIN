package com.payhada.admin.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ErrorCode {
	/** 공통 오류 코드 */
	// TODO 다국어처리
	API_STATUS_OK("E0000", "정상"),
	API_BAD_REQUEST("E1001","요청 데이터 입력이 유효하지 않습니다."),
	API_DUP_REQUEST("E0019","이미 요청이 처리되었습니다."),
	API_FAIL_SELECT("E1010","조회 요청 처리 중 오류가 발생했습니다."),
	API_FAIL_INSERT("E1021","등록 요청 처리 중 오류가 발생했습니다."),
	API_FAIL_UPDATE("E1031","수정 요청 처리 중 오류가 발생했습니다."),
	API_FAIL_DELETE("E1041","삭제 요청 처리 중 오류가 발생했습니다."),
	// 여기에 추가
	API_PROCESS_ERROR("E1998", "해당 업무 처리 중 오류가 발생했습니다."),
	API_SERVER_ERROR("E9999","서비스중 오류가 발생했습니다."),

	/** 유저/직원 관련 오류 코드 */
	USER_SESSION_EXPIRED("E2000", "세션이 만료 되었습니다."),
	USER_NOT_FOUND("E2001", "등록되지 않은 사용자 입니다."),
	USER_UNAUTHORIZED("E2002", "인증에 실패하였습니다."),
	USER_AUTH_REQUIRED("E2003", "해당 요청에 대한 권한이 없습니다."),

	/** NCP 관련 오류 코드 */
	NCP_MAKE_SIGNATURE_ERR("E3000", "NCP Signature 생성 중 오류가 발생했습니다."),
	NCP_GENERATE_HEADER_ERR("E3001", "NCP Header 생성 중 오류가 발생했습니다."),
	NCP_FAIL_MAIL_SERVICE("E3002", "NCP Mail Service 요청 중 오류가 발생했습니다."),
	;

	@Getter
	private String code;
	@Getter
	private String message;

	/** ############################### 여기부턴 사용 안함 (삭제 예정) ############################### */

	//공통 오류코드
//	SERVICE_OK("E0000","정상"),
//	HAVE_NO_DATA("E0001","조회하신 데이터가 없습니다."),
//	SESSION_DATA_SETTING_ERR("E0003","로그인 서비스작업중 오류가 발생했습니다."),
//	DUPLICATE_KEY_ERR ("E0010","이미 존재하는 데이터 입니다."),
//	ALREADY_REQUSET ("E0019","이미 요청이 처리되었습니다."),
//	BAD_REQUEST("E1001","요청 데이터 입력이 유효하지 않습니다."),
//	DATA_SELECT_ERR("E1010","요청하신 거래 조회 중 오류가 발생했습니다."),
//	DATA_INSERT_ERR("E1021","요청하신 등록 처리 중 오류가 발생했습니다."),
//	DATA_UPDATE_ERR("E1031","요청하신 수정 처리 중 오류가 발생했습니다."),
//	DATA_DELETE_ERR("E1041","요청하신 삭제 처리 중 오류가 발생했습니다."),
	
	//유저 오류코드
//	USER_ALREAY_REGISTERED("E1001","기존에 등록되어있는 이메일입니다."),
//	USER_NOT_DEFINED("E1002","조회하신 사용자를 찾을 수 없습니다."),
//	LOGIN_DATA_MISMACH("E1003","아이디와 비밀번호가 맞지 않습니다."),
//	PIN_NUM_ERR ("E1004","핀번호 오류가 발생했습니다."),
//	USER_CERT_INSERT_ERR("E1005","유저 신분증 정보 등록에러"),
//	RECV_SEARCH_ERR("E1011","수취인 정보 조회 중 오류가 발생했습니다."),
//	PUSH_KEY_ERR ("E1006","push키 오류가 발생했습니다."),
//	PWD_AUTH_FAILED_ERR("E1007","비밀번호 입력 횟수를 초과했습니다."),
//    USER_DISABLED ("E1008","계정이 비활성화되었습니다."),
//    TRADE_LIMIT_USER ("E1009","송금 서비스가 제한된 회원입니다."),
	
	//거래관련 오류코드
//	TRADE_NOT_FOUND("E1101","거래정보를 찾을 수 없습니다. 거래번호를 확인하세요."),
//	BANK_TRADE_REJECTED("E1102","은행 이체조회 거절"),
//	TRADE_AVAILABLE_CHECK_ERR("E1104","이체 거래 가능여부 조회 중 오류가 발생하였습니다."),
//	TRADE_REQ_INSERT_ERR("E1105","이체요청 서비스 등록 중 오류가 발생했습니다."),
//	BANK_TRADE_COMPLETE_REJECTED("E1106","은행 거래완료 처리 오류"),
//	TRADE_COMPLETE_ERR("E1107","거래 완료 처리 중 요류가 발생했습니다."),
//
//	//은행관련오류코드
//	BANK_TRADE_AVAILABLE_CHECK_ERR("E1701","은행 이체 거래 가능여부 조회 중 오류가 발생하였습니다."),
//	BANK_NOT_FOUND("E1702","참가 은행 정보를 확인 할 수 없습니다."),
//	BANK_NAME_ERR("E1703","계좌 실명 조회 오류."),
//	BANK_DEPOSIT_ERR("E1704","은행 이체 거래 서비스 중 오류가 발생했습니다."),
//	BANK_SEND_RESULT("E1705","이체 결과 조회 오류"),
//	DUPLICATE_TRADE ("E1706","이미 이체 내역이 있습니다"),
//	USER_AUTH ("E1707","사용자 인증에 실패하였습니다."),
//	//AVA_ACCT_AMT_LIMIT("E1708","출금 가능 계좌 한도 제한"),
//	//BALANCE_ACCT_AMT_LIMIT("E1709","송금인 계좌 잔액 부족"),
//	BANK_AVA_AMT_ERR ("E1709","잔액조회에 실패하였습니다."),
//	BANK_RECV_ACCT_ERR ("E1710","입금 불가능한 계좌입니다."),
//	BANK_REGISTER_ERR("E1711", "계좌 등록에 실패하였습니다"),
//	BANK_UPDATE_ERR("E1712", "계좌 정보 수정에 실패하였습니다"),
//	BANK_TRANS_NOT_FOUND ("E1713","거래 내역 조회에 실패하였습니다."),
//	BANK_FINTECH_NUM_NOT_FOUND ("E1714","핀테크 번호 조회 오류. 등록 계좌 정보 조회에 실패하였습니다."),
//	BANK_ACCT_CANCEL_ERR ("E1715","계좌해지에 실패하였습니다."),
//
//	//결제관련 오류코드
//	PAYMENT_INIT_ERR("E1201","결제용 신청번호 및 URL조회에 실패하였습니다."),
//	PAYMENT_FAILED("E1202","결제서비스 처리 진행중 오류가 발생했습니다"),
//	PAYMENT_SERVICE_ERR("1203","결제처리 작업중 요류가 발생했습니다."),
//	AVA_AMT_ERR("1204","송금 가능한 금액이 초과되었습니다."),
//	PAYMENT_NOT_COMPLETED("1205","결제 처리가 완료되지 않은 거래입니다."),
//
//	//STR
//	OVER_YEAR_TRADE_LIMIT("E1301","연간 송금 한도액 초과"),
//	OVER_DAY_TRADE_LIMIT("E1302","1일 송금 한도액 초과"),
//	OVER_DAY_TRADE_CNT("E1303","1일 송금 한도횟수 초과"),
//	OVER_WEEK_TRADE_CNT("E1304","주간 송금 한도횟수 초과"),
//	OVER_HOUR_TRADE_CNT("E1305","시간당 한도횟수 초과"),
//	OVER_DAY_TRADE_RECV_CNT("E13051","1일 동일 수취인 수 초과"),
//	STR_RULE_REJECTED("E1306","STR RULE 기준에 일치하지 않습니다."),
//	WLF_SEARCH_ERR("E1307","WLF 서치목록 조회 중 오류가 발생했습니다."),
//	WLF_HIST_SEARCH_ERR("E1308","WLF이력목록 조회 중 오류가 발생했습니다."),
//	AML_FILTER_SERVICE_ERR("E1309","AML 서비스 진행 중 오류가 발생하였습니다."),
//	WLF_LIST_DETECTED("E1310","AML서비스 처리 중 이상목록이 감지되었습니다.[거래정지처리]"),
//	STR_INSERT_ERR("E311", "STR 등록 중 오류가 발생하였습니다"),
//	STR_UPDATE_ERR("E312", "STR 수정 중 오류가 발생하였습니다"),
//
//	//인증오류코드
//	ACCT_CERT_ERR ("E1401","출금 동의 등록 중 오류가 발생했습니다."),
//	ACCT_SEND_AUTH_ERR("E1402","1원 송금 이체 오류가 발생했습니다."),
//	ACCT_AUTH_ERR("E1403","1원 송금 인중 오류가 발생했습니다."),
//	CERT_AUTH_ERR("E1404","신분증 진위 여부  이체 중 오류가 발생했습니다."),
//	KFTC_AUTH_ERR("E1405","KTFC인증 처리 진행 중 오류가 발생했습니다."),
//
//	//환율 오류코드
//	RATE_CALCULRATE_ERR("E1501","환율 금액 조회 및 계산 중 오류가 발생했습니다."),
//	RATE_LIST_SEARCH_ERR("E1502","환율 목록 조회 중 오류가 발생했습니다."),
//	EMPTY_NATION_RATE("E1503","국가 환율 정보 조회에 실패하였습니다."),
//
//	TRANSACTION_FAILED_ERR("9101","내부거래 처리 중 오류가 발생했습니다."),
//
//	// 수수료 오류코드
//	FEE_NOT_FOUND("E2101", "계약수수료 조회에 실패하였습니다."),
//
//	// 토큰인증 오류코드
//	TOKEN_AUTH_ERR ("E1602","이메일 인증 만료기간이 초과되었습니다."),
//
//	// 이메일 오류코드
//	EMAIL_FAIL_ERR("E2001", "이메일 전송이 실패했습니다."),
//
//	// 서비스과금 오류코드
//	CHARGE_NOT_FOUND("E3101", "서비스 사용내역 조회에 실패하였습니다"),
//
//	// DB 암복호화 오류코드
//	KEY_NOT_FOUND("E3102", "키 오류가 발생했습니다."),
//
//	//push
//	PUSH_SEND_FAIL("E4101", "푸시서비스 이용 중 오류가 발생했습니다."),
//
//	BUSINESS_PROCESS_ERR("E9001","해당 업무 처리 중 오류가 발생했습니다."),
//	NOT_DEFINED_ERR("E9999","서비스중 오류가 발생했습니다.")	,
//	PERMISSION_NOT_DEFINED ("E0004","권한이 없습니다.");

}
