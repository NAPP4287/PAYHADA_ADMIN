package com.payhada.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

    private String agent_cd;
    private String agent_nm;
    private String branch_parent_cd;
    private String branch_cd;
    private String branch_nm;
    private String branch_type;
    private String branch_status;
    private String branch_tel;
    private String branch_zip_cd;
    private String branch_addr;
    private String branch_addr_detail;
    private String regist_date;
    private String regist_id;
    private String modify_date;
    private String modify_id;
}
