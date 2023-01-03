package com.payhada.admin.dao;

import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberDAO {

	public int getMemberId(String id);
	public MemberDTO findLoginInfo(MemberDTO dto);
	public MemberDTO findOtpDetail(String memberNo);
	public MemberDTO getMemberLoginDetail(MemberDTO dto);

	public int updateEnabled(MemberDTO dto);
	public int updateFailCount(String id);
	public int updateOtp(MemberDTO dto);
	public MemberDTO getFailAttempts(String id);

	public MemberDTO memberLoginCheck(MemberDTO dto);
	public int getMemberCnt(MemberDTO dto);
	public List<MemberDTO> getMember(MemberDTO dto);
	public MemberDTO getMemberDetail(MemberDTO dto);

    List<LoginDTO> selectEmployees();

	LoginDTO selectEmployeeByUserNo(String userNo);
//	public List<PermDTO> searchSeqById(PermDTO permDto);
//	public String getMemberDetailPhoneNumber(MemberDTO dto);
//	public int registerMember(MemberDTO dto);
//	public int updateMember(MemberDTO dto);
//	public int deleteMember(int member_no);
//	public List<MemberDTO> getAllMember(MemberDTO dto);
//	public List<PermDTO> getMemberPerm(String member_no);
//	public List<MenuDTO> getMenu();
//	public List<MenuDTO> getParentMenu();
//
//	public List<PermDTO> updatePerm(PermDTO permDto);
//	public List<MenuDTO> getPermMenu(String role);
//	public int insertMemberAccessLog(MemberDTO memberDto);
//	public int deletePermit(MemberDTO dto);
//	public int updateMemberPermit(PermDTO dto);
}
