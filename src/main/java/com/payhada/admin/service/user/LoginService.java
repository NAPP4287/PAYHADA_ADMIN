package com.payhada.admin.service.user;

import com.payhada.admin.common.util.StringUtils;
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

    public LoginDTO login(LoginDTO loginDTO) {
        return loginDAO.selectEmployeeWithLoginId(loginDTO);
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

    public void updateLastLoginDate(String userNo) {
        loginDAO.updateLastLoginDate(userNo);
    }

    public void loginSuccessful(String userNo) {
        // 비밀번호 실패 카운트, 잠금 시간 초기화
        LoginDTO loginDTO = LoginDTO.builder()
                .userNo(userNo)
                .pwdFailCnt(0)
                .lockStartTime(null)
                .build();
        loginDAO.updateEmployeeFailureData(loginDTO);

        // last_login_date 컬럼 업데이트
        loginDAO.updateLastLoginDate(userNo);
    }

    public String generateLoginOTP(String userNo) {
        String otpCode = StringUtils.generateRandomNumberString();

        LoginDTO loginDTO = LoginDTO.builder()
                .userNo(userNo)
                .otpCode(otpCode)
                .build();

        loginDAO.generateOTPCode(loginDTO);

        return otpCode;
    }
}
