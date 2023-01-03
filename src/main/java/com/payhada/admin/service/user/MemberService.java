package com.payhada.admin.service.user;

import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.MemberDTO;

import java.util.List;

public interface MemberService {
	public int getMemberId(String id);
	public MemberDTO getMemberWithId(MemberDTO dto);
	public MemberDTO getMemberOtpDetail(String memberNo);
//	public Map<String, Object> getMembeSendEmail(MemberDTO dto);

	public MemberDTO getFailAttempts(String id);
	public void updateFailAttempts(MemberDTO dto);
	public void updateFailCount(String id);
	public void updateOtp(MemberDTO dto);

    List<LoginDTO> getEmployees();

	LoginDTO getEmployee(String userNo);
//
//	public int getMemberCnt(MemberDTO dto);
//	public List<MemberDTO> getMember(MemberDTO dto);
//	public MemberDTO getMemberDetail(MemberDTO dto);
//	public String getMemberDetailPhoneNumber(MemberDTO dto);
//	public List<PermDTO> searchSeqById(PermDTO permDto);
//	public int registerMember(MemberDTO dto);
//	public int updateMember(MemberDTO dto);
//	public int deleteMember(int member_no);
//	public List<MemberDTO> getAllMember(MemberDTO dto);
//	public List<PermDTO> getMemberPerm(String member_no);
//	public List<MenuDTO> getMenu();
//	public List<MenuDTO> getParentMenu();
//	public List<PermDTO> updatePerm(PermDTO permDto);
//	public List<MenuDTO> getPermMenu(String role);
//	public int insertMemberAccessLog(MemberDTO memberDto);
//	public int deletePermit(MemberDTO dto);
//	public int updateMemberPermit(PermDTO dto);
}
