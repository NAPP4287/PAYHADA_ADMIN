package com.payhada.admin.controller;

import com.payhada.admin.common.util.StringUtils;
import com.payhada.admin.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class MainController {

    private final MailService mailService;

    public MainController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping(value="/test")
    public ResponseEntity<?> getTest(HttpServletRequest request) throws Exception {
        log.debug("# # test api # # ");
        log.debug("Session: " + request.getSession().getAttribute("SPRING_SECURITY_CONTEXT"));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/test2")
    public String test() {
        return "ok";
    }

    @GetMapping("/test3")
    public void test3() {
        String email = "sw.jeon@payhada.com";
        String otpCode = StringUtils.generateRandomNumberString();
        String authDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try {
            Map<String, Object> res = mailService.sendAdminAuthMail(email, otpCode, authDate);
            log.info("res : {}", res);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}

