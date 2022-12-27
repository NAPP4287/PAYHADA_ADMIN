package com.payhada.admin.dao;

import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDAO {
	LoginDTO selectMemberWithLoginId(LoginDTO loginDTO);
}
