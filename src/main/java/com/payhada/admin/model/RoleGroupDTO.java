package com.payhada.admin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@NoArgsConstructor
@ToString
public class RoleGroupDTO {
    private String roleGroupCode;
    private String roleGroupName;
    private String displayRoleName;
    private String useYn;
    private String description;
}
