package com.payhada.admin.dao;

import com.payhada.admin.model.EmployeeInfoDTO;
import com.payhada.admin.model.EmployeeRoleMappDTO;
import com.payhada.admin.model.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginDAO {
	LoginDTO selectEmployeeWithLoginId(LoginDTO loginDTO);

	LoginDTO selectEmployeeLoginInfo(String userNo);

	List<EmployeeRoleMappDTO> selectEmployeeRoles(String userNo);

	void updateEmployeeFailureData(LoginDTO loginDTO);

	void updateLastLoginDate(String userNo);

	void generateOTPCode(LoginDTO loginDTO);

    EmployeeInfoDTO selectEmployeeInfo(String userNo);

	void updateEmployeeLanguageCd(LoginDTO loginDTO);
}
