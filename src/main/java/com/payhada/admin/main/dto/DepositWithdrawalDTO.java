package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositWithdrawalDTO {

    private int deposit_idx;
    private int account_idx;
    private int trade_idx;
    private String deposit_amt;
    private String withdrawal_amt;
    private String bank_balance_amt;
    private String remittance_type;
    private String memo;
    private String nation_nm;
    private String regist_date;
    private String regist_id;

    private SearchDTO searchDto;

}
