package com.payhada.admin.service.user;

import com.payhada.admin.common.keymanager.AesKeyConfig;
import com.payhada.admin.common.setting.CommonPropertiesDTO;
import com.payhada.admin.dao.MemberDAO;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.MemberDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService  {	
	private final Logger log = LoggerFactory.getLogger(getClass());

	public static final int MAX_FAILED_ATTEMPTS = 5;
	private static final long LOCK_TIME = 600 * 1000;

	@Autowired
	MemberDAO memberDao;

	@Autowired
	AesKeyConfig keyConfig;

	@Autowired
	CommonPropertiesDTO propertiesDto;


	@Override
	public int getMemberId(String id) {
		return memberDao.getMemberId(id);
	}

	@Override
	public MemberDTO getMemberWithId(MemberDTO dto) throws UsernameNotFoundException {
		MemberDTO member= memberDao.findLoginInfo(dto);	
		return member;
	}

	@Override
	public MemberDTO getMemberOtpDetail(String memberNo) throws UsernameNotFoundException {
		MemberDTO member= memberDao.findOtpDetail(memberNo);	
		return member;
	}

//	@Override
//	public Map<String, Object> getMembeSendEmail(MemberDTO dto) throws UsernameNotFoundException {
//
//		Map<String, String> header	= new LinkedHashMap<String, String>();
//		Map<String, String> body	= new LinkedHashMap<String, String>();
//		HttpStatus httpStatus		= HttpStatus.OK;
//		String data					= "";
//
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
//		String today = dateFormat.format(dto.getOtp_date());
//
//		// try {
//			body.put("email",		dto.getEmail());
//			body.put("name",		dto.getId());
//			body.put("code",		dto.getOtp_code());
//			body.put("date",		today);
//			body.put("user_no",		Integer.toString(dto.ge()));
//			body.put("reg_id",		"Payhada Admin");
//			body.put("upt_id",		"Payhada Admin");
//
//			Iterator<String> keys = body.keySet().iterator();
//			while( keys.hasNext() ){
//				String key = keys.next();
//				log.info("["+key+"]"+body.get(key));
//			}
//
//			System.out.println(propertiesDto);
//			HttpConnector conn = new HttpConnector();
//			conn.setConnectionUrl("http://" + propertiesDto.getPayhada_server_url() + ":" + propertiesDto.getPayhada_server_core_port()+"/mail/send_post/admin_email_auth");
//			data = conn.sendHttpGet(header, body);
//			log.info("[DATA = ]"+data);
//
//		// } catch (Exception e) {
//		// 	//TODO: handle exception
//		// 	httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//		// 	throw new BusinessException(ErrorCode.BUSINESS_PROCESS_ERR);
//		// }
//		Gson gson = new Gson();
//		return gson.fromJson(data, Map.class);
//		// return data;
//	}
//
//	@Override
//	public int getMemberCnt(MemberDTO dto) {
//		return memberDao.getMemberCnt(dto);
//	}
	@Override
	public MemberDTO getFailAttempts(String id) {
		return memberDao.getFailAttempts(id);
	}

	@Override
	public void updateFailCount(String id) {
		memberDao.updateFailCount(id);
	}

	@Override
	public void updateFailAttempts(MemberDTO dto) {
		memberDao.updateEnabled(dto);
	}
//
	@Override
	public void updateOtp(MemberDTO dto) {
		memberDao.updateOtp(dto);
	}

	@Override
	public List<LoginDTO> getEmployees() {
		return memberDao.selectEmployees();
	}

	@Override
	public LoginDTO getEmployee(String userNo) {
		return memberDao.selectEmployeeByUserNo(userNo);
	}

	//
//	@Override
//	public List<MemberDTO> getMember(MemberDTO dto) {
//		List<MemberDTO> list = memberDao.getMember(dto);
//		SupportPack pack = new SupportPack();
//		pack.memberNumberingForm(list);
//		return list;
//	}
//	@Override
//	public MemberDTO getMemberDetail(MemberDTO dto) {
//		SupportPack pack = new SupportPack();
//		return pack.memberDetailNumberingForm(memberDao.getMemberDetail(dto));
//	}
//
//	@Override
//	public String getMemberDetailPhoneNumber(MemberDTO dto) {
//		return memberDao.getMemberDetailPhoneNumber(dto);
//	}
//
//	@Override
//	public List<PermDTO> searchSeqById(PermDTO permDto) {
//		return memberDao.searchSeqById(permDto);
//	}
//
//	@Override
//	public int registerMember(MemberDTO dto) {
//		if(dto.getPwd()!=null) {
//			dto.setPwd(SHAEncrpytion.encrpyt512(dto.getPwd()));
//		}
//		return memberDao.registerMember(dto);
//	}
//	@Override
//	public int updateMember(MemberDTO dto) {
//		return memberDao.updateMember(dto);
//	}
//	@Override
//	public int deleteMember(int member_no) {
//		return memberDao.deleteMember(member_no);
//	}
//	@Override
//	public List<MemberDTO> getAllMember(MemberDTO dto) {
//		List<MemberDTO> list = memberDao.getAllMember(dto);
//		return list;
//	}
//
//	@Override
//	public List<PermDTO> getMemberPerm(String member_no) {
//		return memberDao.getMemberPerm(member_no);
//	}
//
//	@Override
//	public List<MenuDTO> getMenu() {
//		return memberDao.getMenu();
//	}
//
//	@Override
//	public List<MenuDTO> getParentMenu() {
//		return memberDao.getParentMenu();
//	}
//
//	@Override
//	public List<PermDTO> updatePerm(PermDTO permDto) {
//		return memberDao.updatePerm(permDto);
//	}
//
//
//	@Override
//	public List<MenuDTO> getPermMenu(String role) {
//		List<MenuDTO> menu = memberDao.getMenu();
//		List<MenuDTO> permMenu = new ArrayList<>();
//
//		String[] arr		= role.split(", ");
//		String lMenuCd = "";
//		for(int i = 0; i < arr.length; i++) {
//			for(MenuDTO k : menu){
//				if(arr[i].equals(k.getSeq())){
//					if(!lMenuCd.equals(k.getLmenu_cd())){
//						lMenuCd = k.getLmenu_cd();
//						k.setMain(true);
//					}
//					k.setSub(true);
//					permMenu.add(k);
//					break;
//				}
//			}
//		}
//		return permMenu;
//	}
//
//	@Override
//	public int insertMemberAccessLog(MemberDTO memberDto) {
//		return memberDao.insertMemberAccessLog(memberDto);
//	}
//
//	@Override
//	public int deletePermit(MemberDTO dto) {
//		return memberDao.deletePermit(dto);
//	}
//
//	@Override
//	public int updateMemberPermit(PermDTO dto) {
//		return memberDao.updateMemberPermit(dto);
//	}

}
