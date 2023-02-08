package com.payhada.admin.controller.user;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.CommonResponse;
import com.payhada.admin.exception.BusinessException;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * 로그인 세션 체크 (테스트용! 사용안함)
     * @return
     * @throws Exception
     */
    @GetMapping(value="/login/session")
    public ResponseEntity<?> getSessionInfo(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Object userInfo = session.getAttribute("userInfo");
        if(userInfo == null) {
            throw new BusinessException(ResponseCode.USER_SESSION_EXPIRED);
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userInfo", userInfo);
        resultMap.put("code", ResponseCode.API_STATUS_OK.getCode());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/loginCheck")
    public ResponseEntity<CommonResponse> loginCheck() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 2차 인증까지 완료 했는지 확인
        if (principal instanceof LoginDTO && ((LoginDTO) principal).getAuthenticateStep() == 3) {
            LoginDTO loginDTO = (LoginDTO) principal;
            Map<String, Object> loginInfo = loginService.getLoginInfoJson(loginDTO);

            return ResponseCode.API_STATUS_OK.toResponseEntity(loginInfo);
        }

        return ResponseCode.USER_SESSION_EXPIRED.toResponseEntity();
    }
}
