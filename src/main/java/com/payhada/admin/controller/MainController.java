package com.payhada.admin.controller;

import com.payhada.admin.model.EmployeeRoleMappDTO;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import com.payhada.admin.service.user.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v2")
public class MainController {

    private final LoginService loginService;
    private final MemberService memberService;

    public MainController(LoginService loginService, MemberService memberService) {
        this.loginService = loginService;
        this.memberService = memberService;
    }

    @GetMapping(value="/test")
    public ResponseEntity<?> getTest(HttpServletRequest request) throws Exception {
        log.debug("# # test api # # ");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<LoginDTO>> getEmployees() {
        List<LoginDTO> list = memberService.getEmployees();
        for (LoginDTO dto : list) {
            List<EmployeeRoleMappDTO> roles = loginService.getEmployeeRoles(dto);
            dto.setEmployeeRoleMappDTOList(roles);
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping("/employee/{userNo}")
    public ResponseEntity<LoginDTO> getEmployee(@PathVariable String userNo) {
        LoginDTO dto = memberService.getEmployee(userNo);
        List<EmployeeRoleMappDTO> roles = loginService.getEmployeeRoles(dto);
        dto.setEmployeeRoleMappDTOList(roles);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/employee/{userNo}")
    public ResponseEntity<LoginDTO> updateEmployee(@PathVariable String userNo) {
        return ResponseEntity.ok().build();
    }
}

