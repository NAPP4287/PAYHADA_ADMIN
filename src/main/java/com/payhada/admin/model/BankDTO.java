package com.payhada.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDTO {

    private int seq;
    private String remittance_nm;
    private String agent_nm;
    private String agent_cd;
    private String product_cd;
    private String nation_cd;
    private String send_fee;
    private String currency;
    private String reg_dt;
    private String reg_id;
    private String nation_nm;
    private String agent_margin_amt;
    private String recv_currency;
    private String payment_type;
    private String total_fee;
    private String upt_dt;
    private String upt_id;

    private SearchDTO searchDto;

}
