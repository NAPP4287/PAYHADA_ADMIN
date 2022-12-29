package com.payhada.admin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@NoArgsConstructor
@ToString
public class EmployeeRoleMappDTO {
    private String userNo;
    private String roleGroupCode;
    private String roleLevel;
}
