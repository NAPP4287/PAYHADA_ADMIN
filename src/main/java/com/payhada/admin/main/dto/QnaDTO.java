package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaDTO {

    private int seq;
    private String user_no;
    private String name;
    private String category_cd;
    private String subject;
    private String contents;
    private String status;
    private String reg_id;
    private String reg_dt;
    private String answer_dt;
    private String reply_status;

    private SearchDTO searchDTO;
}
