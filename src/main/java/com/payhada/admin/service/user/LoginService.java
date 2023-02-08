package com.payhada.admin.service.user;

import com.payhada.admin.common.util.StringUtils;
import com.payhada.admin.dao.LoginDAO;
import com.payhada.admin.model.EmployeeInfoDTO;
import com.payhada.admin.model.EmployeeRoleMappDTO;
import com.payhada.admin.model.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    // OTP 제한 시간
    private static final long OTP_LIMIT_TIME = 3;

    private final LoginDAO loginDAO;

    public LoginDTO getLoginDTO(LoginDTO loginDTO) {
        return loginDAO.selectEmployeeWithLoginId(loginDTO);
    }

    public List<EmployeeRoleMappDTO> getEmployeeRoles(LoginDTO loginDTO) {
        return loginDAO.selectEmployeeRoles(loginDTO.getUserNo());
    }

    public void updateLoginFailureData(LoginDTO failureDTO) {
        loginDAO.updateEmployeeFailureData(failureDTO);
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

    public Map<String, String> generateLoginOTP(String userNo) {
        String otpCode = StringUtils.generateRandomNumberString();
        String otpDate = LocalDateTime.now().plusMinutes(OTP_LIMIT_TIME)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LoginDTO loginDTO = LoginDTO.builder()
                .userNo(userNo)
                .otpCode(otpCode)
                .otpDate(otpDate)
                .build();

        loginDAO.generateOTPCode(loginDTO);

        Map<String, String> otpData = new HashMap<>();
        otpData.put("otpCode", otpCode);
        otpData.put("otpDate", otpDate);

        return otpData;
    }

    public EmployeeInfoDTO getEmployeeInfo(String userNo) {
        return loginDAO.selectEmployeeInfo(userNo);
    }

    public String[] getAllRoleGroupNames() {
        return loginDAO.selectAllGroupNames();
    }

    public void updateEmployeeLanguage(LoginDTO loginDTO) {
        loginDAO.updateEmployeeLanguageCd(loginDTO);
    }

    public Map<String, Object> getLoginInfoJson(LoginDTO loginDTO) {
        List<Map<String, String>> roleGroupList = loginDTO.getRoleGroupJson();

        String languageCd = loginDTO.getLanguageCd();
        if (org.springframework.util.StringUtils.isEmpty(languageCd)) {
            languageCd = "ko";
        }

        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("userNo", loginDTO.getUserNo());
        loginInfo.put("loginId", loginDTO.getId());
        loginInfo.put("languageCd", languageCd);
        loginInfo.put("roleGroupList", roleGroupList);

        return loginInfo;
    }
}
