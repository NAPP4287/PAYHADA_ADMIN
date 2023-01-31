package com.payhada.admin.common.setting;

public class Constants {
	public static final String SUCCESS						= "00";		//결과성공
	public static final String REQUEST						= "01";		//요청
	public static final String FAILTURE						= "11";		//결과실패
	
	//user_status
	public static final String USER_OK						= "00";		// 정상
	public static final String USER_ID_CERT_STAND_BY		= "01";		// 본인인증(신분증)대기
	public static final String USER_ACCT_CERT_STAND_BY		= "02";		// 계좌인증대기
	public static final String USER_EMAIL_NOT_CHECKING		= "03";		// 이메일인증대기
	public static final String USER_LIMIT_TRADE				= "04";		// 송금서비스제한회원
	public static final String USER_WITHDRAWAL_STAND_BY		= "10";		// 탈퇴예정
	public static final String USER_WITHDRAWAL_COMPLETE		= "11";		// 탈퇴완료
	public static final String USER_WITHDRAWAL_REQUEST		= "12";		// 탈퇴요청
	public static final String USER_DISABLED				= "21"; 	// 비활성화
	
	// member_status
	public static final String MEMBER_OK					= "00";
	
	//trade_status (trade)
	public static final String TRADE_COMPLETE				= "00";
	public static final String TRADE_REQUEST				= "01";
	public static final String TRADE_PAYMENT_COMPLETE		= "02";
	public static final String TRADE_TRANSFER_READY			= "03";
	public static final String TRADE_WAIT_TRANSFER			= "04";
	public static final String TRADE_FAILTURE				= "10";
	public static final String TRADE_CANCEL					= "11";
	public static final String TRADE_REFUND					= "12";
	public static final String TRADE_STOP					= "21";
	
	//trade_status (tradeComplete)
	public static final String TRADE_SETTLEMENT_COMPLETE	= "00";
	public static final String TRADE_SETTLEMENT_STAND_BY	= "01";
	
	// trade_bank_status 
	public static final String TRADE_BANK_COMPLETE			= "00"; //완료
	public static final String TRADE_BANK_STAND_BY			= "01"; //미완료
	public static final String TRADE_BANK_REQ				= "02"; //진행중
	
	// trade cancel_detail
	public static final String CANCEL_WEEK_COUNT			= "01";
	public static final String CANCEL_DAY_COUNT				= "02";
	public static final String CANCEL_HOUR_COUNT			= "03";
	public static final String CANCEL_SAME_PERSON			= "04";
	public static final String CANCEL_OUTSTANDING_PAYMENT	= "05";
	public static final String CANCEL_AML_CHECK				= "06";
	
	// prefunding
	public static final String PREFUNDING_DEPOSIT			="입금";
	public static final String PREFUNDING_WITHDRAWAL		="출금";
	
	//settlement_status
	public static final String SETTLEMENT_COMPLETE			="00";	
	public static final String SETTLEMENT_REQUEST			="01";
	
	//wlf_status
	public static final String WLF_OK						= "00";		//WLF정상
	public static final String WLF_DETECTED					= "11";		//WLF이상감지
	
	//aml_status
	public static final String AML_OK						= "00";		//AML정상
	public static final String AML_REQUEST					= "01";		//AML요청
	public static final String AML_ADMIN_OK					= "02";		//WLF관리자승인
	public static final String AML_REJECTED					= "11";		//WLF불가
	
	//str_status
	public static final String STR_OK						= "00";		//STR적용
	public static final String STR_REQUEST					= "01";		//STR요청
	public static final String STR_REJECTED					= "11";		//STR불가
	
	//product_cd
	public static final String PRODUCT_SEND					= "S";		//당발
	public static final String PRODUCT_RECV					= "R";		//타발
	
	//recv_status
	public static final String RECV_OK						= "00";		//정상
	public static final String RECV_CERT_CHECKING			= "01";		//인증대기
	public static final String RECV_DELETE					= "11";		//삭제
	
	//미등록
	public static final String LOG_SUCCESS					= "00";		//정상
	public static final String LOG_REQUEST					= "01";		//요청
	public static final String LOG_FAILTURE					= "11";		//실패
	
	//account_type
	public static final String ACCT_WITHDRAWAL				= "01";		// 송금계좌 (출금)
	public static final String ACCT_DEPOSIT					= "02"; 	// 수취계좌 (입금)
	
	// account_type
	public static final String WITHDRAWAL					= "1";		// 출금이체
	public static final String DEPOSIT						= "2"; 		// 입금이체
	
}
