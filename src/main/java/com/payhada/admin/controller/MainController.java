package com.payhada.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class MainController {

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

}

