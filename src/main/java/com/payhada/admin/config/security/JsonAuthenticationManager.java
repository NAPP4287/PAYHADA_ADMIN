package com.payhada.admin.config.security;

import com.payhada.admin.common.util.SHAEncryption;
import com.payhada.admin.model.EmployeeRoleMappDTO;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JsonAuthenticationManager implements AuthenticationManager {
    private final LoginService loginService;

    public JsonAuthenticationManager(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("JsonAuthenticationManager is running...");

        LoginDTO loginParam = new LoginDTO();
        LoginDTO loginResult;

        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        loginParam.setId(username);
        loginResult = loginService.loginWithLoginIdAndPwd(loginParam);

        // 계정 존재여부 확인
        if (loginResult == null) throw new UsernameNotFoundException("등록되지 않은 사용자 입니다.");

        // 계정 잠김 상태 확인
        if (!loginResult.isAccountNonLocked()) {
            throw new LockedException("계정이 잠김 상태 입니다.");
        }

        // 패스워드 확인
        if (!SHAEncryption.encrypt512(password).equals(loginResult.getPwd())) throw new BadCredentialsException("패스워드가 일치하지 않습니다.");

        // 권한 조회
        List<EmployeeRoleMappDTO> roles = loginService.getEmployeeRoles(loginResult);
        loginResult.setEmployeeRoleMappDTOList(roles);

        return new UsernamePasswordAuthenticationToken(loginResult, authentication.getCredentials(), loginResult.getAuthorities());
    }

}
