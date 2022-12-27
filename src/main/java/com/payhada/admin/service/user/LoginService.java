package com.payhada.admin.service.user;

import com.payhada.admin.common.util.SHAEncryption;
import com.payhada.admin.dao.LoginDAO;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginDAO loginDAO;

    public LoginDTO loginWithLoginIdAndPwd(LoginDTO loginDTO) throws Exception {
        loginDTO.setPwd(SHAEncryption.encrypt512(loginDTO.getPwd()));
        return loginDAO.selectMemberWithLoginId(loginDTO);
    }
}
