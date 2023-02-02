package com.payhada.admin.controller;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.CommonResponse;
import com.payhada.admin.exception.BusinessException;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class MainController {

    private final LoginService loginService;

    public MainController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping(value="/test")
    public ResponseEntity<?> getTest(HttpServletRequest request) {
        log.debug("# # test api # # ");
        log.debug("Session: " + request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/test2")
    public String test() throws BusinessException {
        throw new BusinessException(ResponseCode.API_BAD_REQUEST);
//        return "ok";
    }

    @GetMapping("/language")
    public ResponseEntity<CommonResponse> changeLanguage(@RequestParam(defaultValue = "ko") String language,
                                                       @RequestParam(required = false) String userNo,
                                                       HttpSession session) {
        if (userNo != null) {
            LoginDTO updateDTO = LoginDTO.builder()
                    .userNo(userNo)
                    .languageCd(language)
                    .build();
            loginService.updateEmployeeLanguage(updateDTO);
        }

        session.setAttribute("locale", new Locale(language));

        return ResponseCode.API_STATUS_OK.toResponseEntity();
    }

}
