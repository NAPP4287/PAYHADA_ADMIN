package com.payhada.admin.dao;

import com.payhada.admin.model.EmployeeRoleMappDTO;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.MemberDTO;
import com.payhada.admin.model.RoleGroupDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginDAO {
	LoginDTO selectMemberWithLoginId(LoginDTO loginDTO);

    List<EmployeeRoleMappDTO> selectEmployeeRoles(String userNo);

    RoleGroupDTO selectRoleGroupByCode(String roleGroupCode);
}
