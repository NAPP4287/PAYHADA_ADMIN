package com.payhada.admin.config.security;

import com.payhada.admin.common.util.SHAEncryption;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//@Component
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

        Authentication auth = null;

        LoginDTO loginParam = new LoginDTO();
        LoginDTO loginResult;
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

        try {
            String username = authentication.getPrincipal().toString();
            String password = authentication.getCredentials().toString();
            loginParam.setId(username);
//            loginParam.setPwd(password);
            loginResult = loginService.loginWithLoginIdAndPwd(loginParam);

            if (loginResult == null) throw new UsernameNotFoundException("아이디를 찾을 수 없습니다.");

            if (!SHAEncryption.encrypt512(password).equals(loginResult.getPwd())) throw new BadCredentialsException("패스워드가 일치하지 않습니다.");

            List<SimpleGrantedAuthority> employeeAuthorities = loginService.getEmployeeAuthorities(loginResult);
            authorities.addAll(employeeAuthorities);

            auth = new UsernamePasswordAuthenticationToken(loginResult.getUserNo(), loginResult.getPwd(), authorities);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return auth;
    }

}
