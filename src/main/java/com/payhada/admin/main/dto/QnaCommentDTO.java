package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnaCommentDTO {

    private int comment_seq;
    private int seq;
    private int comment_no;
    private String comment_content;
    private String reg_dt;
    private String reg_id;
    private String reply_status;

}
