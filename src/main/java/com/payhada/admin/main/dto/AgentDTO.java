package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentDTO {

    private String agent_cd;
    private String agent_nm;
    private String agent_type;
    private String agent_tel;
    private String nation_cd;
    private String nation_nm;
    private String agnet_status;
    private String regist_date;
    private String regist_id;
    private String modify_date;
    private String modify_id;

    private SearchDTO searchDto;
}
