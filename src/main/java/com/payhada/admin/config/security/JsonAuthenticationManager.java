package com.payhada.admin.config.security;

import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//@Component
@RequiredArgsConstructor
@Slf4j
@Component
public class JsonAuthenticationManager implements AuthenticationManager {
    private final LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication auth = null;
        log.debug("JsonAuthenticationManager is running...");
        String username, password = null;
        LoginDTO loginParam = new LoginDTO();
        LoginDTO loginResult = null;
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        try {
            username = authentication.getPrincipal().toString();
            password = authentication.getCredentials().toString();
            loginParam.setId(username);
            loginParam.setPwd(password);
            loginResult = loginService.loginWithLoginIdAndPwd(loginParam);
            if(loginResult != null) {
                authorities.add(new SimpleGrantedAuthority("TMP")); //TODO add user role
                auth = new UsernamePasswordAuthenticationToken(loginResult, null, authorities);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auth;
    }

}
