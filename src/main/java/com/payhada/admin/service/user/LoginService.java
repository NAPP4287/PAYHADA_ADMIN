package com.payhada.admin.service.user;

import com.payhada.admin.dao.LoginDAO;
import com.payhada.admin.model.EmployeeRoleMappDTO;
import com.payhada.admin.model.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginDAO loginDAO;

    public LoginDTO loginWithLoginIdAndPwd(LoginDTO loginDTO) {
        return loginDAO.selectMemberWithLoginId(loginDTO);
    }

    public List<EmployeeRoleMappDTO> getEmployeeRoles(LoginDTO loginDTO) {
        return loginDAO.selectEmployeeRoles(loginDTO.getUserNo());
    }

    public void resetLoginFailureData(String userNo) {
        LoginDTO loginDTO = LoginDTO.builder()
                .userNo(userNo)
                .pwdFailCnt(0)
                .lockStartTime(null)
                .build();
        loginDAO.updateEmployeeFailureData(loginDTO);
    }

    public void updateLoginFailureData(LoginDTO failureDTO) {
        loginDAO.updateEmployeeFailureData(failureDTO);
    }
}
