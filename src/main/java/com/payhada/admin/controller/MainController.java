package com.payhada.admin.controller;

import com.payhada.admin.code.ErrorCode;
import com.payhada.admin.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class MainController {

    @GetMapping(value="/test")
    public ResponseEntity<?> getTest(HttpServletRequest request) throws Exception {
        log.debug("# # test api # # ");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

