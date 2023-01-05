//package com.payhada.admin.model;
//
//import lombok.*;
//import org.apache.ibatis.type.Alias;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//@ToString
//@Data
//@NoArgsConstructor
//@Alias("loginDTO")
//public class LoginDTO {
//    private String id;
//    private String pwd;
//    private String code;
//    private String memberNo;
//}

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

    private String otpCode;

    private String userNo;

    private Integer pwdFailCnt;

    private String lockStartTime;

    private List<EmployeeRoleMappDTO> employeeRoleMappDTOList;

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
    public LoginDTO(String id, String pwd, String otpCode, String userNo, Integer pwdFailCnt,
                    String lockStartTime, List<EmployeeRoleMappDTO> employeeRoleMappDTOList) {
        this.id = id;
        this.pwd = pwd;
        this.otpCode = otpCode;
        this.userNo = userNo;
        this.pwdFailCnt = pwdFailCnt;
        this.lockStartTime = lockStartTime;
        this.employeeRoleMappDTOList = employeeRoleMappDTOList;
    }
}
