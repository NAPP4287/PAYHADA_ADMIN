package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO {

    private int menu_idx;
    private String lmenu_cd;
    private String lmenu_nm;
    private String smenu_cd;
    private String smenu_nm;
    private String url;
    private String use_yn;
    private String menu_icon;
    private String regist_date;
    private String regist_id;
    private String modify_date;
    private String modify_id;
    private boolean main = false;
    private boolean sub = false;

}
