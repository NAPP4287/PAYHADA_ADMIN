package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private int admin_idx;
    private String name;
    private String id;
    private String pwd;
    private String status;
    private String branch_cd;
    private String agent_cd;
    private String agent_nm;
    private String email;
    private String tel;
    private String mobile;
    private String job_tel;
    private String zip_code;
    private String addr;
    private String addr_detail;
    private String ip;
    private String pwd_fail_cnt;
    private String user_role;
    private String change_pw_yn;
    private String change_pw_date;
    private String is_enabled;
    private String lock_time;
    private String otp_code;
    private String otp_date;
    private String join_date;
    private String leave_date;
    private String last_login_date;
    private String regist_date;
    private String regist_id;
    private String modify_date;
    private String modify_id;

    private SearchDTO searchDto;

}
