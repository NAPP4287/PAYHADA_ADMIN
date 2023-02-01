package com.payhada.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.common.setting.Response;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static com.payhada.admin.common.util.MessageSourceUtils.getMessage;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    // 계정 잠금 시간
    private static final long LOCK_MIN = 30;

    private final LoginService loginService;
    private final ObjectMapper objectMapper;

    public CustomAuthenticationFailureHandler(LoginService loginService) {
        this.loginService = loginService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        int resultCode;
        String resultMsg = exception.getMessage();
        Map<String, Object> data = null;

        Response responseDTO;
        ResponseCode responseCode;
        try {
            LoginDTO loginDto = objectMapper.readValue(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8), LoginDTO.class);

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
                responseCode = ResponseCode.USER_NOT_FOUND;
            } else if (exception instanceof BadCredentialsException) {
                if (exception.getMessage().equals("PASSWORD")) {
                    // 비밀번호 실패 카운트 변경
                    // 5회 이상 실패 시 계정 잠금
                    LoginDTO failureDTO = loginService.login(loginDto);
                    String userNo = failureDTO.getUserNo();
                    int pwdFailCnt = failureDTO.getPwdFailCnt() + 1;
                    String lockStartTime = null;

                    responseCode = ResponseCode.MISMATCH_PASSWORD;
                    data = new HashMap<>();
                    data.put("pwdFailCnt", pwdFailCnt);

                    if (pwdFailCnt > 4) {
                        lockStartTime = LocalDateTime.now().plusMinutes(LOCK_MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        // 잠김 시간 이후 다시 5번의 기회를 위해 비밀번호 실패 카운트 초기화
                        pwdFailCnt = 0;
                    }

                    failureDTO = LoginDTO.builder()
                            .userNo(userNo)
                            .pwdFailCnt(pwdFailCnt)
                            .lockStartTime(lockStartTime)
                            .build();

                    loginService.updateLoginFailureData(failureDTO);
                } else {
                    responseCode = ResponseCode.MISMATCH_OTP;
                }
            } else if (exception instanceof LockedException) {
                responseCode = ResponseCode.LOCKED_ACCOUNT;
            } else if (exception instanceof InsufficientAuthenticationException) {
                responseCode = ResponseCode.UNAUTHENTICATED_1;
            } else if (exception instanceof CredentialsExpiredException) {
                responseCode = ResponseCode.TIMEOUT_OTP;
            } else {
                responseCode = ResponseCode.API_SERVER_ERROR;
            }

            responseDTO = Response.create(responseCode.getCode(), data);
        } catch (Exception e) {
            log.error(e.getMessage());

            responseDTO = Response.create(ResponseCode.API_SERVER_ERROR.getCode());
        }

        if (jsonConverter.canWrite(responseDTO.getClass(), jsonMimeType)) {
            jsonConverter.write(responseDTO, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }
}
