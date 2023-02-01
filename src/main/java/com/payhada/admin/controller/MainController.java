package com.payhada.admin.controller;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.Response;
import com.payhada.admin.common.util.MessageSourceUtils;
import com.payhada.admin.exception.BusinessException;
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

    @GetMapping("/locale")
    public ResponseEntity<Response> changeLocale(@RequestParam(defaultValue = "kr") String locale,
                                                 HttpSession session) {
        session.setAttribute("locale", new Locale(locale));

        return ResponseCode.API_STATUS_OK.toResponseEntity();
    }

}

