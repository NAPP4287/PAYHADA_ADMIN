package com.payhada.admin.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
public class LoginDTO implements UserDetails {

    private static final long LOCK_MIN = 30;

    private String id;                      // 로그인 아이디

    private String pwd;                     // 패스워드

    private String otpCode;                 // 발급된 OTP Code

    private String otpDate;                 // OTP Code 발금 날짜

    private String userNo;                  // 유저번호

    private Integer pwdFailCnt;             // 로그인 실패 횟수

    private String lockStartTime;           // 로그인 락 걸린 시간

    private String languageCd;              // 마지막으로 선택한 언어코드

    private String lastLoginDate;           // 마지막으로 로그인한 일시

    private List<EmployeeAgentRoleMappDTO> employeeAgentRoleMappDTOList = new ArrayList<>();      // 권한 리스트 (유저-에이전트가 1:N 매핑될 경우를 대비해 리스트로 전달)

    private String secret;                  // 사용자가 입력한 OTP Code

    private Integer authenticateStep;       // 인증 단계 (1: ID/PW 인증 성공, 2: OTP 인증 진행 중, 3: OTP 인증 성공)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (EmployeeAgentRoleMappDTO dto : employeeAgentRoleMappDTOList) {
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
                    String lockStartTime, String languageCd, String lastLoginDate, List<EmployeeAgentRoleMappDTO> employeeRoleMappDTOList,
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
        this.employeeAgentRoleMappDTOList = employeeRoleMappDTOList;
        this.secret = secret;
        this.authenticateStep = authenticateStep;
    }

    public void setAuthenticateStep(Integer authenticateStep) {
        this.authenticateStep = authenticateStep;
    }

    public List<Map<String, String>> getRoleGroupJson() {
        return this.employeeAgentRoleMappDTOList.stream()
                .map(dto -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("roleCd", dto.getRoleGroupCode());
                    map.put("roleNm", dto.getRoleGroupName());
                    map.put("nationCd", dto.getNationCode());
                    map.put("agentCd", dto.getAgentCode());
                    return map;
                }).collect(Collectors.toList());
    }
}
