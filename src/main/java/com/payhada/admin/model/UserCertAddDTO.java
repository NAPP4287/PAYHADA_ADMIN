package com.payhada.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCertAddDTO {

    private String cert_add_seq;
    private String user_no;
    private String cert_add_type;
    private String cert_add_type_nm;
    private String cert_add_image;
    private String cert_status;
    private byte[] cert_add_image_byte;
    private String reg_dt;
    private String upt_dt;
    private String upt_id;
    private String cancel_reason;
    private byte[] cert_image_front;

}
