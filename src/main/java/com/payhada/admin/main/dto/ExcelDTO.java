package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelDTO {

    private int excel_idx;
    private String trade_type_status;
    private String regist_date;
    private String regist_id;

    private SearchDTO searchDto;

}
