package com.payhada.admin.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@ToString
@Data
@NoArgsConstructor
@Alias("loginDTO")
public class LoginDTO implements UserDetails {

    private static final long LOCK_MIN = 30;

    private String id;

    private String pwd;

    private String otpCode; // 발급된 OTP Code

    private String otpDate;

    private String userNo;

    private Integer pwdFailCnt;

    private String lockStartTime;

    private String languageCd;

    private String lastLoginDate;

    private List<EmployeeRoleMappDTO> employeeRoleMappDTOList = new ArrayList<>();

    private String secret; // 사용자가 입력한 OTP Code

    // 인증 단계 (1: ID/PW 인증 성공, 2: OTP 인증 진행 중, 3: OTP 인증 성공)
    private Integer authenticateStep;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (EmployeeRoleMappDTO dto : employeeRoleMappDTOList) {
            authorities.add(new SimpleGrantedAuthority(dto.getRoleGroupCode()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.pwd;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (this.lockStartTime == null) return true;

        LocalDateTime lockStartDateTime = LocalDateTime.parse(this.lockStartTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return LocalDateTime.now().isAfter(lockStartDateTime.plusMinutes(LOCK_MIN));
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public LoginDTO(String id, String pwd, String otpCode, String otpDate, String userNo, Integer pwdFailCnt,
                    String lockStartTime, String languageCd, String lastLoginDate, List<EmployeeRoleMappDTO> employeeRoleMappDTOList,
                    String secret, Integer authenticateStep) {
        this.id = id;
        this.pwd = pwd;
        this.otpCode = otpCode;
        this.otpDate = otpDate;
        this.userNo = userNo;
        this.pwdFailCnt = pwdFailCnt;
        this.lockStartTime = lockStartTime;
        this.languageCd = languageCd;
        this.lastLoginDate = lastLoginDate;
        this.employeeRoleMappDTOList = employeeRoleMappDTOList;
        this.secret = secret;
        this.authenticateStep = authenticateStep;
    }

    public void setAuthenticateStep(Integer authenticateStep) {
        this.authenticateStep = authenticateStep;
    }

    public List<Map<String, String>> getRoleGroupJson() {
        return this.employeeRoleMappDTOList.stream()
                .map(dto -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("roleGroupCode", dto.getRoleGroupCode());
                    map.put("roleGroupName", dto.getRoleGroupName());
                    return map;
                }).collect(Collectors.toList());
    }
}
