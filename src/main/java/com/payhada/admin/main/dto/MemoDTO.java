package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoDTO {

    private int memo_idx;
    private String user_no;
    private String memo_type;
    private String memo;
    private String regist_date;
    private String regist_id;
    private String modify_date;
    private String modify_id;

    private SearchDTO searchDto;
}
