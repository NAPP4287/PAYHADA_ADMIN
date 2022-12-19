package com.payhada.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeesDTO {

    private int seq;
    private String bank_cd;
    private String depositor;
    private String acct_nm;
    private String agent_cd;
    private String account_nm;
    private String bank_type;
    private String reg_dt;
    private String reg_id;
    private String upt_dt;
    private String upt_id;
    private String etc;
    private String use_yn;
    private String currency;
    private String api_tran_id;
    private String rsp_code;
    private String rsp_message;
    private int api_tran_dtm;
    private String bank_tran_id;
    private String bank_tran_date;
    private String bank_code_tran;
    private String bank_rsp_code;
    private String bank_rsp_message;
    private String bank_code_std;
    private String bank_code_sub;
    private String bank_name;
    private String account_num;
    private String account_holder_info_type;
    private String account_holder_info;
    private String account_holder_name;
    private String account_type;
    private String savings_bank_name;
    private String account_seq;
    private String bank_account;

    private SearchDTO searchDto;

}
