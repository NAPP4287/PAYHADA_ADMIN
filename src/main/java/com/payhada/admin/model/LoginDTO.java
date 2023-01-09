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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    private String lastLoginDate;

    private List<EmployeeRoleMappDTO> employeeRoleMappDTOList = new ArrayList<>();

    private String agentCode;

    private String secret; // 사용자가 입력한 OTP Code

    private Boolean isAuthenticatedByOTP;

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
                    String lockStartTime, String lastLoginDate, List<EmployeeRoleMappDTO> employeeRoleMappDTOList,
                    String agentCode, String secret, Boolean isAuthenticatedByOTP) {
        this.id = id;
        this.pwd = pwd;
        this.otpCode = otpCode;
        this.otpDate = otpDate;
        this.userNo = userNo;
        this.pwdFailCnt = pwdFailCnt;
        this.lockStartTime = lockStartTime;
        this.lastLoginDate = lastLoginDate;
        this.employeeRoleMappDTOList = employeeRoleMappDTOList;
        this.agentCode = agentCode;
        this.secret = secret;
        this.isAuthenticatedByOTP = isAuthenticatedByOTP;
    }

    public void setIsAuthenticatedByOTP(Boolean isAuthenticatedByOTP) {
        this.isAuthenticatedByOTP = isAuthenticatedByOTP;
    }
}
