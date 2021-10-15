package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TermsDTO {

    public int seq;
    private String terms_type;
    private String terms;
    private String use_yn;
    private String reg_dt;
    private String reg_id;
    private String upt_dt;
    private String upt_id;

}
