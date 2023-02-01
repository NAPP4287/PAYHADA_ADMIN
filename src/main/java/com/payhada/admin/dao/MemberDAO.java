package com.payhada.admin.dao;

import com.payhada.admin.model.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO {

	int getMemberId(String id);
	MemberDTO findLoginInfo(MemberDTO dto);
	MemberDTO findOtpDetail(String memberNo);
	MemberDTO getMemberLoginDetail(MemberDTO dto);

	int updateEnabled(MemberDTO dto);
	int updateFailCount(String id);
	int updateOtp(MemberDTO dto);
	MemberDTO getFailAttempts(String id);

	MemberDTO memberLoginCheck(MemberDTO dto);
	int getMemberCnt(MemberDTO dto);
	List<MemberDTO> getMember(MemberDTO dto);
	MemberDTO getMemberDetail(MemberDTO dto);
}
