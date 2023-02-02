package com.payhada.admin.config.security;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.common.setting.CommonResponse;
import com.payhada.admin.service.MailService;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import static com.payhada.admin.common.util.MessageSourceUtils.getMessage;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginService loginService;

    private final MailService mailService;

    private final RequestCache requestCache = new HttpSessionRequestCache();

    public CustomAuthenticationSuccessHandler(LoginService loginService, MailService mailService) {
        this.loginService = loginService;
        this.mailService = mailService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        LoginDTO loginDTO = (LoginDTO) authentication.getPrincipal();
        CommonResponse commonResponse;
        ResponseCode responseCode;
        Map<String, Object> data = null;

        try {
            Integer authenticateStep = loginDTO.getAuthenticateStep();
            if (authenticateStep == 1) {
                // 1차 인증 (username / password) 일 경우 OTP 번호 생성 후 업데이트, 이메일 전송
                Map<String, String> otpData = loginService.generateLoginOTP(loginDTO.getUserNo());
                String otpCode = otpData.get("otpCode");
                String otpDate = otpData.get("otpDate");

                // 이메일 전송
                ////////////
//                EmployeeInfoDTO employeeInfoDTO = loginService.getEmployeeInfo(loginDTO.getUserNo());
//                String email = employeeInfoDTO.getEmail();

//                mailService.sendAdminAuthMail(email, otpCode, otpDate);
                ///////////

                // 메일전송 기능 구현 전 까지 임시로 사용
                data = new HashMap<>();
                data.put("otpCode", otpCode);

                responseCode = ResponseCode.SUCCESSFUL_LOGIN_1;
            } else if (authenticateStep == 2) {
                // 2차 인증 (OTP) 중 코드 미일치 일 경우 (authenticateStep == 2)
                responseCode = ResponseCode.MISMATCH_OTP;
            } else {
                // 2차 인증 성공
                // 로그인 성공 시 데이터 업데이트
                loginService.loginSuccessful(loginDTO.getUserNo());

                // locale 설정
//                setLocale(request);

                // JsonAuthenticationManager 에서 넣어준 권한을 가져옴
                List<Map<String, String>> roleGroupList = loginDTO.getEmployeeRoleMappDTOList().stream()
                        .map(dto -> {
                            Map<String, String> map = new HashMap<>();
                            map.put("roleGroupCode", dto.getRoleGroupCode());
                            map.put("roleGroupName", dto.getRoleGroupName());
                            return map;
                        }).collect(Collectors.toList());

                data = new HashMap<>();
                data.put("userNo", loginDTO.getUserNo());
                data.put("loginId", loginDTO.getId());
                data.put("roleGroupList", roleGroupList);

                responseCode = ResponseCode.SUCCESSFUL_LOGIN_2;
            }

            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                requestCache.removeRequest(request, response);
                clearAuthenticationAttributes(request);
            }
        } catch (Exception e) {
            log.error("CustomAuthenticationSuccessHandler Error :: {}", e.getMessage());

            responseCode = ResponseCode.API_SERVER_ERROR;
        }

        commonResponse = CommonResponse.create(responseCode.getCode(), data);

        response.setStatus(responseCode.getStatus());

        // application/json
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        if (jsonConverter.canWrite(commonResponse.getClass(), jsonMimeType)) {
            jsonConverter.write(commonResponse, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }

    private void setLocale(HttpServletRequest request, String locale) {
        HttpSession session = request.getSession();
        session.setAttribute("locale", new Locale(locale));
    }
}
