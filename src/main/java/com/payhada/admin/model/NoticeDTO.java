package com.payhada.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDTO {

    public int seq;
    public String subject;
    public String contents;
    public String status;
    public String start_date;
    public String end_date;
    public String unlimit_yn;
    private byte[] file;
    private String file_nm;
    public String category;
    public String category_cd;
    public String push_type;
    public String reg_dt;
    public String reg_id;
    public String upt_dt;
    public String upt_id;

    private SearchDTO searchDto;
}
