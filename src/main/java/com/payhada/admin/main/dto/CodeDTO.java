package com.payhada.admin.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeDTO {

    private String group_code;
    private String code;
    private String group_name;
    private String code_name;
    private String comment;
    private String regist_date;
    private String regist_id;

    private AgentDTO agentDto;
    private SearchDTO searchDto;
}
