package com.payhada.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.ResponseDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
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

        ResponseDTO responseDTO;
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
                resultCode = 404;
            } else if (exception instanceof BadCredentialsException) {
                resultCode = 400;
                if (exception.getMessage().equals("PASSWORD")) {
                    // 비밀번호 실패 카운트 변경
                    // 5회 이상 실패 시 계정 잠금
                    LoginDTO failureDTO = loginService.login(loginDto);
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

                    resultMsg = "패스워드가 일치하지 않습니다.";
                } else {
                    resultMsg = "OTP 코드가 일치하지 않습니다.";
                }

            } else if (exception instanceof LockedException) {
                resultCode = 400;
            } else if (exception instanceof InsufficientAuthenticationException) {
                resultCode = 400;
            } else {
                resultCode = 500;
                resultMsg = "서비스중 오류가 발생했습니다.";
            }

            responseDTO = ResponseDTO.builder()
                    .resultCode(resultCode)
                    .resultMsg(resultMsg)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());

            responseDTO = ResponseDTO.builder()
                    .resultCode(500)
                    .resultMsg("서비스중 오류가 발생했습니다.")
                    .build();
        }

        if (jsonConverter.canWrite(responseDTO.getClass(), jsonMimeType)) {
            jsonConverter.write(responseDTO, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }
}
