package com.payhada.admin.service.user;

import com.payhada.admin.common.util.SHAEncryption;
import com.payhada.admin.dao.LoginDAO;
import com.payhada.admin.model.EmployeeRoleMappDTO;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.RoleGroupDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {
    private final LoginDAO loginDAO;

    public LoginDTO loginWithLoginIdAndPwd(LoginDTO loginDTO) throws Exception {
//        loginDTO.setPwd(SHAEncryption.encrypt512(loginDTO.getPwd()));
        return loginDAO.selectMemberWithLoginId(loginDTO);
    }

    public List<SimpleGrantedAuthority> getEmployeeAuthorities(LoginDTO loginResult) {
        List<EmployeeRoleMappDTO> employeeRoles = loginDAO.selectEmployeeRoles(loginResult.getUserNo());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (EmployeeRoleMappDTO dto : employeeRoles) {
            String roleGroupCode = dto.getRoleGroupCode();
            RoleGroupDTO roleGroupDTO = loginDAO.selectRoleGroupByCode(roleGroupCode);

            if (roleGroupDTO != null) {
                String roleGroupName = roleGroupDTO.getRoleGroupName();
                authorities.add(new SimpleGrantedAuthority(roleGroupName));
            }
        }

        return authorities;
    }
}
