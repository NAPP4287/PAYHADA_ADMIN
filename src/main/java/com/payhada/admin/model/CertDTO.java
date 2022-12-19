package com.payhada.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertDTO {

    private int idx;
    private String user_no;
    private String name;
    private String ssn;
    private String birth;
    private String cert_issue_date;
    private String acct_name;
    private String bank_cd;
    private String bank_nm;
    private String acct_no;
    private String cert_type;
    private String cert_nm;
    private byte[] cert_image_front;
    private byte[] cert_image_back;
    private String cert_verify1;
    private String cert_verify2;
    private String cert_verify3;
    private String cert_verify4;
    private String cert_verify8;
    private String cert_verify10;
    private String cert_verify_yn;
    private String acct_verify_yn;
    private String join_verify_yn;
    private String aml_status;
    private String cert_issue_agency;
    private String cert_result_nm;
    private String cert_front_yn;
    private String cert_back_yn;
    private String serial_no;
    private String kftc_auth_verify;
    private String kftc_acct_verify;
    private String cdd_date;
    private String email_date;
    private String kftc_date;
    private String cert_date;
    private String acct_verify_date;
    private String acct_date;
    private String complete_date;

    private String cert_add_type;
    private String cert_add_type_nm;
    private String cert_add_image;
    private String cert_add_verify_yn;
    private String cert_add_date;
    private String cert_add_seq;
    private String cancel_reason;

}
