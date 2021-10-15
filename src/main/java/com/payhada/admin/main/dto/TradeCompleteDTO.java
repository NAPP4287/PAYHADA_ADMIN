package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeCompleteDTO {

    private int trade_idx;
    private String match_trade_idx;
    private String agent_cd;
    private String trade_status;
    private String trade_type;
    private String trade_reason;
    private String trade_reason_detail;
    private String product_cd;
    private String send_currency;
    private String recv_currency;
    private String trade_req_date;
    private String trade_send_date;
    private String req_amt;
    private String recv_amt;
    private String trade_amt;
    private String usd_amt;
    private String confirm_idx;
    private String confirm_nm;
    private String send_no;
    private String send_nm;
    private String send_acct;
    private String send_addr;
    private String send_aml_no;
    private String recv_no;
    private String recv_nm;
    private String recv_acct;
    private String recv_addr;
    private String recv_aml_no;
    private String telegraphic_transfer_amt;
    private String customer_transfer_amt;
    private String dollar_rate;
    private String settlement_idx;
    private String settlement_round;
    private String total_margin_amt;
    private String fee_idx;
    private String send_fee;
    private String margin_amt;
    private String profit;

    private String regist_date;
    private String regist_id;
    private String modify_date;
    private String modify_id;

    private SearchDTO searchDto;

}
