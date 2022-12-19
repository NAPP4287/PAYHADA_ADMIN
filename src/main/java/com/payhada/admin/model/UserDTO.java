package com.payhada.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String user_no;
    private String id;
    private String status;
    private String name;
    private String en_first_nm;
    private String en_last_nm;
    private String en_name;
    private String sex;
    private String birth;
    private String ssn;
    private String tel;
    private String phone;
    private String zip_cd;
    private String addr;
    private String addr_detail;
    private String nation_cd;
    private String nation_nm;
    private String agent_cd;
    private String agent_nm;
    private String reg_path;
    private String sms_yn;
    private String pwd_ch_dt;
    private int pwd_fail_cnt;
    private String reg_dt;
    private String reg_id;
    private String upt_dt;
    private String ipt_id;
    private String job_cd;
    private String job;
    private String job_detail;
    private String job_nm;
    private String dep_nm;
    private String job_class;
    private String job_type;
    private String job_zip_cd;
    private String job_addr;
    private String job_addr_detail;
    private String job_tel;
    private String income;
    private String job_income_chk;
    private String city;
    private String state;
    private String income_detail;
    private String cdd_dt;
    private String pwd;
    private String email_yn;
    private String upt_id;
    private String job_city;
    private String job_state;
    private String user_role;
    private String secession;
    private String cert_issue_dt;
    private String acct_name;
    private String db_deletion_dt;

    private String fin_num;
    private String pin;
    private String bank_cd;
    private String bank_nm;

    private int tot_cnt1;
    private int tot_cnt2;
    private int tot_cnt3;
    private int tot_cnt4;
    private int tot_cnt5;

    private int rnum;
    private int userCnt;
    private SearchDTO searchDto;

    private String newPin;
    private String serial_no;

    private String cert_verify1;
    private String cert_verify2;
    private String cert_verify3;
    private String cert_verify4;
    private String cert_verify5;
    private String cert_verify6;
    private String cert_verify7;
    private String cert_verify8;
    private String cert_verify9;
    private String cert_verify10;

    private String cert_front_yn;
    private String cert_back_yn;

    private String acct_no;

    private String withdrawal_yn;
    private String app_version;

    private String token;

    private String key;
    private int pin_fail_cnt;
    private String nationality_cd;
    private String nationality_nm;
    private String perm_yn;

    //	인증정보
    private String seq;
    private String cert_type;
    private String cert_nm;
    private byte[] cert_image_front;
    private byte[] cert_image_back;
    private String cert_verify_yn;
    private String acct_verify_yn;
    private String join_verify_yn;
    private String aml_status;
    private String cert_issue_agency;
    private String cert_result_nm;
    private String kftc_auth_verify;
    private String kftc_acct_verify;
    private String email_dt;
    private String kftc_dt;
    private String acct_verify_dt;

    //	로그인
    private String ip;
    private String user_agent;
    private String user_agent_nm;
    private String login_yn;
    private String mobile_agent_nm;

}
