package com.payhada.admin.model;

import lombok.*;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ToString
@Data
@NoArgsConstructor
@Alias("loginDTO")
public class LoginDTO {
    private String id;
    private String pwd;
    private String code;
    private String memberNo;
    private String userNo;
}
