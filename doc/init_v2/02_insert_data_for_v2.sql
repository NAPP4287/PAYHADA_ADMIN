-- 
-- 해당 쿼리를 적용하기 전에 아래 사항들을 체크 합니다.
-- 1. role_group 테이블에 저장할 권한 그룹 리스트가 동일한지
-- 2. menu_mng 에 저장될 메뉴 링크가 정상적으로 등록되어 있는지
-- 3. employee_agent_role_mapp 에 저장할 에이전트 코드가 정상적으로 등럭되어 있는지
--

-- 
-- MASTER 권한 그룹 코드 : 0000 
-- MASTER 권한을 가진 유저의 권한 레벨 : 0
-- 권한 레벨 : 0 (마스터), 1~9(높을수록 권한이 높음)
-- 권한 레벨 초기 세팅 : 1~5
-- 


-- move payhada.tb_agent -> payhada_v2.agent 
insert into payhada_v2.agent (agent_code, agent_name, agent_type, agent_tel, nation_code, status_code, regist_date, regist_user_no) 
select agent_cd, agent_nm, agent_type, agent_tel, nation_cd, status, reg_dt
    , (select case 
             when tm.member_no < 100 then concat('E','00000', tm.member_no)
             when (tm.member_no >= 100 and tm.member_no < 1000) then concat('E','0000', tm.member_no)
             when (tm.member_no >= 1000 and tm.member_no < 10000) then concat('E','000', tm.member_no)
             else concat('E', tm.member_no) end 
        from payhada_v2.tb_member tm where tm.id = ta.reg_id
    )
from payhada_v2.tb_agent ta
order by ta.reg_dt;

select "payhada_v2.agent done";

-- move payhada.tb_member -> payhada_v2.emplayee & payhada_v2.employee_info & payhada_v2.employee_agent_role_mapp
insert into payhada_v2.employee (user_no, login_id, pwd, otp_code, otp_date, pwd_fail_cnt, lock_start_time, status_code
    , login_type, change_pwd_yn, pwd_ch_date, last_login_date, regist_date, regist_user_no)
select (select case 
             when tm.member_no < 100 then concat('E','00000', tm.member_no)
             when (tm.member_no >= 100 and tm.member_no < 1000) then concat('E','0000', tm.member_no)
             when (tm.member_no >= 1000 and tm.member_no < 10000) then concat('E','000', tm.member_no)
             else concat('E', tm.member_no) end 
        )
        , tm.id
        , tm.pwd 
        , tm.otp_code
        , tm.otp_dt
        , tm.pwd_fail_cnt
        , tm.lock_time
        , tm.status
        , '01'
        , tm.change_pw_yn
        , tm.pwd_ch_dt
        , tm.last_login_dt
        , tm.reg_dt
        , (select case 
             when tm2.member_no < 100 then concat('E','00000', tm2.member_no)
             when (tm2.member_no >= 100 and tm2.member_no < 1000) then concat('E','0000', tm2.member_no)
             when (tm2.member_no >= 1000 and tm2.member_no < 10000) then concat('E','000', tm2.member_no)
             else concat('E', tm2.member_no) end 
          from payhada_v2.tb_member tm2 where tm2.id = tm.reg_id
        )
from payhada_v2.tb_member tm
order by tm.reg_dt;

select "payhada_v2.employee done";


insert into payhada_v2.employee_info (user_no, user_name, email, tel_num, mobile_num, zip_code, address, address_detail
    , join_date, leave_date) 
select (select case 
             when tm.member_no < 100 then concat('E','00000', tm.member_no)
             when (tm.member_no >= 100 and tm.member_no < 1000) then concat('E','0000', tm.member_no)
             when (tm.member_no >= 1000 and tm.member_no < 10000) then concat('E','000', tm.member_no)
             else concat('E', tm.member_no) end 
        )
        , tm.name
        , tm.email
        , tm.tel
        , tm.mobile
        , tm.zip_code
        , tm.addr 
        , tm.addr_detail
        , tm.join_dt
        , tm.leave_dt
from payhada_v2.tb_member tm
order by tm.reg_dt;

select "payhada_v2.employee_info done";

-- move payhada.tb_code > user_role -> payhada_v2.role_group
insert into payhada_v2.role_group (role_group_code, role_group_name, agent_cd, use_yn, description, regist_date, regist_user_no)
values ('0000', 'MASTER', 'Y', 'Payhada Admin Master', now(), null)
    , ('4100000101', 'ADMIN', 'Y', '(주)페이하다 Admin', now(), null)
    , ('4100000102', 'DEVELOPER', 'Y', '(주)페이하다 Developer', now(), null)
    , ('4100000103', 'MANAGER', 'Y', '(주)페이하다 Manager', now(), null)
    , ('4100000104', 'USER', 'Y', '(주)페이하다 User', now(), null)
    , ('0360001001', 'ADMIN', 'Y', '페이하다호주지점 Admin', now(), null)
    , ('0360001002', 'DEVELOPER', 'Y', '페이하다호주지점 Developer', now(), null)
    , ('0360001003', 'MANAGER', 'Y', '페이하다호주지점 Manager', now(), null);

select "payhada_v2.role_group done";


insert into payhada_v2.employee_agent_role_mapp (user_no, agent_code, role_group_code, branch_code, job_tel_num, ip, regist_date, regist_user_no)
select (select case
                   when tm.member_no < 100 then concat('E','00000', tm.member_no)
                   when (tm.member_no >= 100 and tm.member_no < 1000) then concat('E','0000', tm.member_no)
                   when (tm.member_no >= 1000 and tm.member_no < 10000) then concat('E','000', tm.member_no)
                   else concat('E', tm.member_no) end
)
     , tm.agent_cd
     , case
           when tm.agent_cd = '41000001' and tm.user_role like 'A%' then '4100000101'
           when tm.agent_cd = '41000001' and tm.user_role like 'D%' then '4100000102'
           when tm.agent_cd = '41000001' and tm.user_role like 'M%' then '4100000103'
           when tm.agent_cd = '41000001' and tm.user_role like 'U%' then '4100000104'
           when tm.agent_cd = '03600010' and tm.user_role like 'A%' then '0360001001'
           when tm.agent_cd = '03600010' and tm.user_role like 'D%' then '0360001002'
           when tm.agent_cd = '03600010' and tm.user_role like 'M%' then '0360001003'
           else '4100000104'
    end
     , tm.branch_cd
     , tm.job_tel
     , tm.ip
     , tm.reg_dt
     , (select case
                   when tm2.member_no < 100 then concat('E','00000', tm2.member_no)
                   when (tm2.member_no >= 100 and tm2.member_no < 1000) then concat('E','0000', tm2.member_no)
                   when (tm2.member_no >= 1000 and tm2.member_no < 10000) then concat('E','000', tm2.member_no)
                   else concat('E', tm2.member_no) end
        from payhada_v2.tb_member tm2 where tm2.id = tm.reg_id
)
from payhada_v2.tb_member tm
where tm.user_role != 'MAS';
order by tm.reg_dt;

select "payhada_v2.employee_agent_role_mapp done";


insert into payhada_v2.employee_agent_role_mapp (user_no, agent_code, role_group_code, branch_code, job_tel_num, ip, regist_date, regist_user_no)
select (select case
                   when tm.member_no < 100 then concat('E','00000', tm.member_no)
                   when (tm.member_no >= 100 and tm.member_no < 1000) then concat('E','0000', tm.member_no)
                   when (tm.member_no >= 1000 and tm.member_no < 10000) then concat('E','000', tm.member_no)
                   else concat('E', tm.member_no) end
)
     , tm.agent_cd
     , '0000'
     , tm.branch_cd
     , tm.job_tel
     , tm.ip
     , tm.reg_dt
     , (select case
                   when tm2.member_no < 100 then concat('E','00000', tm2.member_no)
                   when (tm2.member_no >= 100 and tm2.member_no < 1000) then concat('E','0000', tm2.member_no)
                   when (tm2.member_no >= 1000 and tm2.member_no < 10000) then concat('E','000', tm2.member_no)
                   else concat('E', tm2.member_no) end
        from payhada_v2.tb_member tm2 where tm2.id = tm.reg_id
)
from payhada_v2.tb_member tm
where tm.user_role = 'MAS';
order by tm.reg_dt;

select 'master user created';

-- insert menu_mng & role_menu_mapp 
-- 페이지 개발 후 적용


