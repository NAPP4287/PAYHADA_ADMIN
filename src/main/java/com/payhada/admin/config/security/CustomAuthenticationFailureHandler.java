package com.payhada.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.common.util.SHAEncryption;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler  {

    private static final long LOCK_MIN = 30;

    private final LoginService loginService;
    private final ObjectMapper objectMapper;

    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStragtegy = new DefaultRedirectStrategy();

    public CustomAuthenticationFailureHandler(LoginService loginService) {
        this.loginService = loginService;
        this.objectMapper = new ObjectMapper();
    }

    //TODO 수정 필요
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LoginDTO loginDto = objectMapper.readValue(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8), LoginDTO.class);

        String errormsg = exception.getMessage();
        log.debug("로그인 실패 :: {}", errormsg);
        String failureRedirectUrl = "/login?error";

        /*
        스프링 시큐리티 AuthenticationException 종류
        - UsernameNotFoundException : 계정 없음
        - BadCredentialsException : 비밀번호 미일치
        - AccountStatusException
            - AccountExpiredException : 계정만료
            - CredentialsExpiredException : 비밀번호 만료
            - DisabledException : 계정 비활성화
            - LockedException : 계정 잠김
        */
		if (exception instanceof UsernameNotFoundException) {

		} else if (exception instanceof BadCredentialsException) {
            // 비밀번호 실패 카운트 변경
            // 5회 이상 실패 시 계정 잠금
            LoginDTO failureDTO = loginService.loginWithLoginIdAndPwd(loginDto);
            String userNo = failureDTO.getUserNo();
            int pwdFailCnt = failureDTO.getPwdFailCnt();
            String lockStartTime;
            if (pwdFailCnt > 5) {
                lockStartTime = LocalDateTime.now().plusMinutes(LOCK_MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                // 잠김 시간 이후 다시 5번의 기회를 위해 비밀번호 실패 카운트 초기화
                pwdFailCnt = 0;
            } else {
                lockStartTime = null;
                pwdFailCnt++;
            }
            failureDTO = LoginDTO.builder()
                    .userNo(userNo)
                    .pwdFailCnt(pwdFailCnt)
                    .lockStartTime(lockStartTime)
                    .build();
            loginService.updateLoginFailureData(failureDTO);

		} else if (exception instanceof LockedException) {
		} else if(exception instanceof InsufficientAuthenticationException) {
            errormsg = "otp 인증코드가 필요합니다.";

			// if(errormsg.contains("TIME")){
			// 	session.setAttribute("memberNo", memberNo);
			// 	session.setAttribute("otpCode", null); 
			// }
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
