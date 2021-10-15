package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StrDTO {

    private int idx;
    private String name;
    private String year_limit_amt;
    private String day_limit_amt;
    private String once_limit_amt;
    private String year_limit_cnt;
    private String send_limit_cnt;
    private String time_limit_cnt;
    private String daily_limit_recv;
    private String trade_cancel_batch_date;
    private String status;
    private String regist_date;
    private String violation_guidance;
    private String end_date;

}
