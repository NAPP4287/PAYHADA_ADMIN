package com.payhada.admin.config.security;

import com.payhada.admin.common.util.SHAEncryption;
import com.payhada.admin.model.EmployeeRoleMappDTO;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
public class JsonAuthenticationManager implements AuthenticationManager {

    // OTP 제한 시간
    private static final long OTP_LIMIT_TIME = 3;

    private final LoginService loginService;

    public JsonAuthenticationManager(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("JsonAuthenticationManager is running...");

        LoginDTO loginParam = (LoginDTO) authentication.getPrincipal();
        LoginDTO loginResult = loginService.login(loginParam);

        // 계정 존재여부 확인
        if (loginResult == null) throw new UsernameNotFoundException("등록되지 않은 사용자 입니다.");

        // 계정 잠김 상태 확인
        if (!loginResult.isAccountNonLocked()) throw new LockedException("계정이 잠김 상태 입니다.");

        // 사용자가 입력한 OTP Code
        String secret = loginParam.getSecret();

        // 1차 인증 (username / password) 인지 2차 인증 (secret) 인지 확인
        if (StringUtils.isEmpty(secret)) {
            // 1차 인증 (username / password) 일 경우 패스워드 확인 후 isAuthenticatedByOTP = FALSE
            // 패스워드 확인
            String password = authentication.getCredentials().toString();
            if (!SHAEncryption.encrypt512(password).equals(loginResult.getPwd())) {
                throw new BadCredentialsException("PASSWORD");
            }
            loginResult.setIsAuthenticatedByOTP(false);
        } else {
            // 2차 인증 (secret) 일 경우 OTP 확인 후 권한을 넣어줌, isAuthenticatedByOTP = TRUE
            String otpCode = loginResult.getOtpCode();
            // OTP 제한시간 확인
            LocalDateTime otpDate = LocalDateTime.parse(loginResult.getOtpDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            if (otpDate.plusMinutes(OTP_LIMIT_TIME).isBefore(LocalDateTime.now())) {
                throw new CredentialsExpiredException("OTP 시간이 만료 되었습니다.");
            }
            // OTP 코드 확인
            if (!secret.equals(otpCode)) {
                throw new BadCredentialsException("OTP");
            }
            // 권한 조회
            List<EmployeeRoleMappDTO> roles = loginService.getEmployeeRoles(loginResult);
            loginResult.setEmployeeRoleMappDTOList(roles);
            loginResult.setIsAuthenticatedByOTP(true);
        }

        return new UsernamePasswordAuthenticationToken(loginResult, authentication.getCredentials(), loginResult.getAuthorities());
    }

}
