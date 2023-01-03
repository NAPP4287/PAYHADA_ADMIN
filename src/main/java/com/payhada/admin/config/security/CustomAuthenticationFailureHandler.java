package com.payhada.admin.config.security;

import com.payhada.admin.common.util.SHAEncryption;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.MemberDTO;
import com.payhada.admin.service.user.LoginService;
import com.payhada.admin.service.user.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler  {

    @Autowired
    MemberService memberService;

    @Autowired
    LoginService loginService;

    // private String id ;  			// 로그인 id값이 들어오는 input태그 name
	// private String pwd ;		// 로그인 pw값이 들어오는 input태그 name
	// private String loginRedirectUrl ;		// 로그인 성공시 redirect 할 URL이 지정되어 있는 input태그 name
	// private String exceptionMsgName ;		// 예외 메시지를 REQUEST의 ATTRIBUTE에 저장할 때 사용
	// private String defaultFailureUrl ;		// 화면에 보여줄 url(로그인 화면)

    
    // public CustomAuthenticationFailureHandler(String loginIdName, String loginPasswordName, String loginRedirectUrl,
	// 		String exceptionMsgName, String defaultFailureUrl) {
	// 	this.id = loginIdName;
	// 	this.pwd = loginPasswordName;
	// 	this.loginRedirectUrl = loginRedirectUrl;
	// 	this.exceptionMsgName  = exceptionMsgName;
	// 	this.defaultFailureUrl = defaultFailureUrl;
	// }

    // public String getId() {
	// 	return id;
	// }
	// public void setId(String id) {
	// 	this.id = id;
	// }
	// public String getPwd() {
	// 	return pwd;
	// }
	// public void setPwd(String pwd) {
	// 	this.pwd = pwd;
	// }
	// public String getLoginRedirectUrl() {
	// 	return loginRedirectUrl;
	// }
	// public void setLoginRedirectUrl(String loginRedirectUrl) {
	// 	this.loginRedirectUrl = loginRedirectUrl;
	// }
	// public String getExceptionMsgName() {
	// 	return exceptionMsgName;
	// }
	// public void setExceptionMsgName(String exceptionMsgName) {
	// 	this.exceptionMsgName = exceptionMsgName;
	// }
	// public String getDefaultFailureUrl() {
	// 	return defaultFailureUrl;
	// }
	// public void setDefaultFailureUrl(String defaultFailureUrl) {
	// 	this.defaultFailureUrl = defaultFailureUrl;
	// }

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStragtegy = new DefaultRedirectStrategy();

    //TODO 수정 필요
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");
        String code = request.getParameter("code");
        String memberNo = request.getParameter("memberNo");
        String otpCode = request.getParameter("otpCode");

        HttpSession session = request.getSession();

        System.out.println("failure");

        // String loginId = request.getParameter(id);
		// String loginPw = request.getParameter(pwd);
		// String loginRedirect = request.getParameter(loginRedirectUrl);
		String errormsg = exception.getMessage();
		String failureRedirectUrl = "/login?error";
        
		if(exception instanceof BadCredentialsException) {
			// 잠긴계정인지 확인하여, errormsg변경해준다.
			// boolean userUnLock = true;
            errormsg = "비밀번호가 일치하지 않습니다.";
            failureRedirectUrl = "/login?error=true&msg=badCredentials";
			// userUnLock = failCnt(loginId);
			// if ( !userUnLock )
			// 	errormsg = "비밀번호가 일치하지 않습니다.";
			// else
				// errormsg = messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", null , Locale.KOREA);
		} else if(exception instanceof InternalAuthenticationServiceException) {
            errormsg = "OTP 코드 입력시간 만료(3분제한)";
			session.setAttribute("memberNo", null);
			session.setAttribute("otpCode", null); 

            failureRedirectUrl = "/login?error=true&msg=timeOut";
			// errormsg = messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.InternalAuthentication", null , Locale.KOREA); 
		} else if(exception instanceof DisabledException) {
			session.setAttribute("memberNo", null);
			session.setAttribute("otpCode", null); 

			errormsg = "비활성화 된 계정, 10분 후 로그인 재시도 필요";
		} else if(exception instanceof CredentialsExpiredException) {
			// errormsg = messageSource.getMessage("AccountStatusUserDetailsChecker.expired", null , Locale.KOREA);
		} else if(exception instanceof UsernameNotFoundException) {
			// Object[] args = new String[] { loginId } ;
			session.setAttribute("memberNo", null);
			session.setAttribute("otpCode", null); 
			
			errormsg = "존재하지 않는 ID 입니다.";
		} else if(exception instanceof LockedException) {
			// errormsg = messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.locked", null , Locale.KOREA);
		} else if(exception instanceof InsufficientAuthenticationException) {
            errormsg = "otp 인증코드가 필요합니다.";

			// if(errormsg.contains("TIME")){
			// 	session.setAttribute("memberNo", memberNo);
			// 	session.setAttribute("otpCode", null); 
			// }
			if(memberNo == "" || memberNo.equals("") || memberNo == null ){
                String encPw =  SHAEncryption.encrypt512(pwd);
                
                //로그인용
                LoginDTO loginDTO = new LoginDTO();
                loginDTO.setId(id);
                loginDTO.setPwd(encPw);
                // 2. 아이디 + 비밀번호로 로그인 체크 (비밀번호 횟수 카운트, 비활성화 여부 확인)
                LoginDTO user = null;
                try {
                    user = loginService.loginWithLoginIdAndPwd(loginDTO);

                    failureRedirectUrl = "/login?otp=true&memberNo=" + user.getMemberNo() ;
                    session.setAttribute("memberNo", user.getMemberNo());
                    session.setAttribute("otpCode", user.getCode()); //OTP 코드 임시로 보이게; 추후 삭제 해야함 210802
                } catch (Exception e) {
                    e.printStackTrace();
                    session.setAttribute("memberNo", null);
                    session.setAttribute("otpCode", null);
                }
            }else{
                failureRedirectUrl = "/login?otp=true&memberNo=" + memberNo ;
                session.setAttribute("memberNo", memberNo);
                session.setAttribute("otpCode", otpCode); //OTP 코드 추후 삭제 210802
            }

			
            
			// errormsg = messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.locked", null , Locale.KOREA);
		} 

        // request.setAttribute("id", id);
		// request.setAttribute("pwd", pwd);
		// request.setAttribute("code", code);
		// request.setAttribute("loginRedirectUrl", failureRedirectUrl);
		// request.setAttribute("errorMsg", errormsg);
		

        

        // if(exception instanceof BadCredentialsException) {
        //     // 잠긴계정인지 확인하여, errormsg변경해준다.
        //     boolean userUnLock = true;
        //     userUnLock = failCnt(loginId);
        //     if ( !userUnLock )
        //     errormsg = messageSource.getMessage("AccountStatusUserDetailsChecker.disabled", null , Locale.KOREA);
        //     else
        //     errormsg = messageSource.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", null , Locale.KOREA);
        // }

        
        // request.setAttribute("exceptionMsgName", errormsg);
		
        // request.setAttribute("id", loginId);
		// request.setAttribute("pwd", loginPw);
		// request.setAttribute("loginRedirectUrl", loginRedirect);
		// request.setAttribute("exceptionMsgName", errormsg);
		
		// request.getRequestDispatcher("defaultFailureUrl").forward(request, response);

        // clearAuthenticationAttributes(request);
        
        // resultRedirectStrategy(request, response, authentication);
        // failureRedirectUrl = failureRedirectUrl.(/&/g,"%26").replace(/\+/g,"%2B");
        super.setDefaultFailureUrl(failureRedirectUrl);
        super.onAuthenticationFailure(request, response, exception);

    }

    // protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
    //         Authentication authentication) throws IOException, ServletException {

    //     SavedRequest savedRequest = requestCache.getRequest(request, response);

    //     if ( savedRequest != null ) {
	// 		String targetUrl = savedRequest.getRedirectUrl();
	// 		// log.info( " GO !!! savedRequest.getRedirectUrl : " + targetUrl );
	// 		redirectStragtegy.sendRedirect(request, response, targetUrl);
	// 	}else {
	// 		// log.info( " GO !!! savedRequest.getRedirectUrl : " + defaultUrl );
	// 		redirectStragtegy.sendRedirect(request, response, defaultUrl);
	// 	}
    // }
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session==null) return;
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }



}
