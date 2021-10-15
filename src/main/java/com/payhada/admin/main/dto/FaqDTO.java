package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaqDTO {

    public int seq;
    public String subject;
    public String contents;
    public String status;
    public String category_cd;
    public String start_date;
    public String end_date;
    public String unlimit_yn;
    private byte[] file;
    private String file_nm;
    public String reg_dt;
    public String reg_id;
    public String upd_dt;
    public String upd_id;
    public int totCnt;
    private SearchDTO searchDto;

    public int prev_seq;
    public String prev_subject;
    public int next_seq;
    public String next_subject;

}
