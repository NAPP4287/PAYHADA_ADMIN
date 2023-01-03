package com.payhada.admin.config.security;

import com.payhada.admin.code.ErrorCode;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginService loginService;

    private RequestCache requestCache = new HttpSessionRequestCache();

    protected CustomAuthenticationSuccessHandler(LoginService loginService) {
        this.loginService = loginService;
    }
//    private RedirectStrategy redirectStragtegy = new DefaultRedirectStrategy();

    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        LoginDTO loginDTO = (LoginDTO) authentication.getPrincipal();

        // 로그인 성공 시 비밀번호 실패 카운트, 잠김시간 초기화
        loginService.resetLoginFailureData(loginDTO.getUserNo());

        Map<String, Object> responseMap = new HashMap<>();
        LoginDTO data = null;
        String resultCode = ErrorCode.API_SERVER_ERROR.getCode();
        String resultMsg = "";

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
        }

        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            data = checkUserAuth();
        }//end if

        if(data == null) {
            resultCode = ErrorCode.USER_NOT_FOUND.getCode();
            resultMsg = ErrorCode.USER_NOT_FOUND.getMessage();
        } else {
            resultCode = ErrorCode.API_STATUS_OK.getCode();
            resultMsg = ErrorCode.API_STATUS_OK.getMessage();
        }// end if

        responseMap.put("resultCode", resultCode);
        responseMap.put("resultMsg", resultMsg);
        responseMap.put("data", data);

        // application/json
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

//        HttpSession session = request.getSession();
//        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

        if (jsonConverter.canWrite(responseMap.getClass(), jsonMimeType)) {
            jsonConverter.write(responseMap, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }

    private LoginDTO checkUserAuth() {
        LoginDTO userInfo = null;

        try {
            userInfo = (LoginDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userInfo.setPwd(null);
            System.out.println("# userInfo() : " + userInfo.toString());

            // TODO 권한 관련 코드 추가 예정 & OTP 인증 코드 추가
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userInfo;
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }

//    protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
//            Authentication authentication) throws IOException, ServletException {
//
//        SavedRequest savedRequest = requestCache.getRequest(request, response);
//
//        if ( savedRequest != null ) {
//			String targetUrl = savedRequest.getRedirectUrl();
//			// log.info( " GO !!! savedRequest.getRedirectUrl : " + targetUrl );
//			redirectStragtegy.sendRedirect(request, response, targetUrl);
//		}else {
//			// log.info( " GO !!! savedRequest.getRedirectUrl : " + defaultUrl );
//			redirectStragtegy.sendRedirect(request, response, defaultUrl);
//		}
//
//    }

    // public void clearAuthenticationAttributes(HttpServletRequest request) {
    //     HttpSession session = request.getSession(false);
    //     if(session==null) return;
    //     session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    // }



}
