--
-- Current Database: `payhada_v2`
--

USE `payhada_v2`;

--
-- Table structure for table `role_group`
--

DROP TABLE IF EXISTS `role_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_group`
(
    `role_group_code`          VARCHAR(10)     NOT NULL    COMMENT '권한 그룹 코드',
    `role_group_name`          VARCHAR(15)     NOT NULL    COMMENT '권한 그룹 명',
    `use_yn`           VARCHAR(1)      NOT NULL DEFAULT 'Y'    COMMENT '사용 여부 Y/N',
    `description`      VARCHAR(150)    NULL        COMMENT '설명',
    `regist_date`      DATETIME        NULL DEFAULT CURRENT_TIMESTAMP        COMMENT '등록 일시',
    `regist_user_no`   VARCHAR(10)     NULL      COMMENT '등록자 유저 번호',
    `modify_date`      DATETIME        NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       COMMENT '수정 일시',
    `modify_user_no`   VARCHAR(10)     NULL      COMMENT '수정자 유저 번호',
     PRIMARY KEY (role_group_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='권한 그룹 관리 테이블';

select 'role_group done';

--
-- Table structure for table `menu_mng`
--

DROP TABLE IF EXISTS `menu_mng`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_mng`
(
    `menu_id`        INT(11)         NOT NULL    AUTO_INCREMENT COMMENT '메뉴 아이디', 
    `menu_name`        VARCHAR(15)     NOT NULL    COMMENT '메뉴 명', 
    `menu_name_en`     VARCHAR(150)    NULL        COMMENT '영문 메뉴 명', 
    `menu_link`      VARCHAR(255)    NOT NULL    COMMENT '메뉴 링크', 
    `display_order`  INT(11)         NOT NULL DEFAULT '0'   COMMENT '표시 순서', 
    `use_yn`         VARCHAR(1)      NOT NULL DEFAULT 'Y'    COMMENT '사용 여부 Y/N', 
    `parent_menu_id`       INT(11)         NULL        COMMENT '부모 메뉴 아이디', 
    `icon`           VARCHAR(45)     NULL        COMMENT '아이콘', 
    `regist_date`         DATETIME        NULL DEFAULT CURRENT_TIMESTAMP       COMMENT '등록 일시', 
    `modify_date`         DATETIME        NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       COMMENT '수정 일시', 
     PRIMARY KEY (menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메뉴 관리 테이블';

select 'menu_mng done';

--
-- Table structure for table `role_menu_mapp`
--

DROP TABLE IF EXISTS `role_menu_mapp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu_mapp`
(
    `role_group_code`  VARCHAR(12)    NOT NULL    COMMENT '권한 그룹 코드', 
    `menu_id`  INT(11)        NOT NULL    COMMENT '메뉴 아이디', 
    `modify_date`   DATETIME       NULL   ON UPDATE CURRENT_TIMESTAMP     COMMENT '수정 일시', 
     PRIMARY KEY (role_group_code, menu_id),
     CONSTRAINT `role_menu_mapp_role_group_code_fk` FOREIGN KEY (role_group_code) REFERENCES `role_group` (role_group_code),
     CONSTRAINT `role_menu_mapp_menu_id_fk` FOREIGN KEY (menu_id) REFERENCES `menu_mng` (menu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='권한 - 메뉴 매핑 테이블';

select 'role_menu_mapp done';

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agent` 
(
    `agent_code`     VARCHAR(8)      NOT NULL    COMMENT '에이전트 코드', 
    `agent_name`         VARCHAR(100)    NOT NULL    COMMENT '에이전트 명', 
    `agent_type`   VARCHAR(2)      NOT NULL    COMMENT '에이전트 타입', 
    `agent_tel`    VARCHAR(20)     NULL        COMMENT '전화번호', 
    `nation_code`    VARCHAR(3)      NULL        COMMENT '국가코드', 
    `status_code`    VARCHAR(2)      NULL        COMMENT '상태코드 00: 정상, 11: 미사용', 
    `regist_date`       DATETIME        NULL   DEFAULT CURRENT_TIMESTAMP     COMMENT '등록 일시', 
    `regist_user_no`  VARCHAR(16)     NULL        COMMENT '등록자 유저 번호', 
    `modify_date`       DATETIME        NULL   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP     COMMENT '수정 일시', 
    `modify_user_no`  VARCHAR(16)     NULL        COMMENT '수정자 유저번호', 
     PRIMARY KEY (agent_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='에이전트 관리 테이블';  

select 'agent done';

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE employee
(
    `user_no`        VARCHAR(16)     NOT NULL    COMMENT '유저 번호', 
    `login_id`       VARCHAR(100)    NOT NULL    COMMENT '로그인 아이디', 
    `pwd`            VARCHAR(500)    NOT NULL    COMMENT '패스워드', 
    `login_token`    VARCHAR(200)    NULL        COMMENT '로그인 토큰', 
    `otp_code`       VARCHAR(15)     NULL        COMMENT 'otp 코드', 
    `otp_date`         DATETIME        NULL        COMMENT 'otp 발급 시간', 
    `pwd_fail_cnt`   VARCHAR(225)    NULL        COMMENT '패스워드 오류 횟수', 
    `lock_start_time`      DATETIME        NULL        COMMENT '계정 잠금 시작 시간',
    `status_code`      VARCHAR(2)      NULL        COMMENT '상태코드(00: 정상 01:인증대기 10:탈퇴예정 11:탈퇴완료)', 
    `login_type`     VARCHAR(2)      NULL        COMMENT '로그인 타입', 
    `change_pwd_yn`    VARCHAR(1)      NULL        COMMENT '최초 패스워드 변경 여부',
    `pwd_ch_date`     DATETIME        NULL        COMMENT '비밀번호 변경 일시', 
    `last_login_date`  DATETIME        NULL        COMMENT '마지막 로그인 일시', 
    `regist_date`         DATETIME        NULL  DEFAULT CURRENT_TIMESTAMP        COMMENT '등록 일시', 
    `regist_user_no`    VARCHAR(16)     NULL        COMMENT '등록자 유저 번호', 
    `modify_date`         DATETIME        NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP       COMMENT '수정 일시', 
    `modify_user_no`    VARCHAR(16)     NULL        COMMENT '수정자 유저 번호', 
     PRIMARY KEY (user_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='유저(직원) 마스터 테이블';

select 'employee done';


--
-- Table structure for table `employee_detail`
--

DROP TABLE IF EXISTS `employee_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE employee_info
(
    `user_no`         VARCHAR(16)     NOT NULL    COMMENT '유저 번호', 
    `user_name`         VARCHAR(100)    NOT NULL        COMMENT '유저 이름', 
    `first_name_en`     VARCHAR(100)    NULL        COMMENT '영문 성', 
    `last_name_en`      VARCHAR(100)    NULL        COMMENT '영문 이름', 
    `email`           VARCHAR(100)    NULL        COMMENT '이메일', 
    `tel_num`         VARCHAR(50)     NULL        COMMENT '전화번호', 
    `mobile_num`      VARCHAR(50)     NULL        COMMENT '핸드폰번호', 
    `gender`          VARCHAR(1)      NULL        COMMENT '성별', 
    `birth`           VARCHAR(50)     NULL        COMMENT '생년월일', 
    `nationality_code`  VARCHAR(3)      NULL        COMMENT '국적코드', 
    `zip_code`          VARCHAR(50)     NULL        COMMENT '우편번호', 
    `address`            VARCHAR(500)    NULL        COMMENT '주소', 
    `address_detail`     VARCHAR(100)    NULL        COMMENT '상세주소', 
    `address_en`         VARCHAR(500)    NULL        COMMENT '영문 주소', 
    `address_detail_en`  VARCHAR(200)    NULL        COMMENT '영문 상세 주소', 
    `city`            VARCHAR(100)    NULL        COMMENT '시', 
    `state`           VARCHAR(100)    NULL        COMMENT '주', 
    `join_date`      DATETIME        NULL  DEFAULT CURRENT_TIMESTAMP      COMMENT '입사일',
    `leave_date`     DATETIME        NULL        COMMENT '퇴사일', 
    `modify_date`          DATETIME        NULL  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP      COMMENT '수정 일시', 
    `modify_user_no`     VARCHAR(16)     NULL        COMMENT '수정자 유저 번호', 
    PRIMARY KEY (user_no),
    CONSTRAINT `employee_info_user_no_fk` FOREIGN KEY (user_no) REFERENCES `employee` (user_no)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='직원 정보 관리 테이블';

select 'employee_info done';


--
-- Table structure for table `employee_agent_role_mapp`
--

DROP TABLE IF EXISTS `employee_agent_role_mapp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE employee_agent_role_mapp
(
    `user_no`      VARCHAR(16)     NOT NULL    COMMENT '유저 번호',
    `agent_code`     VARCHAR(8)      NOT NULL    COMMENT '에이전트 코드',
    `role_group_code`   VARCHAR(10)        NOT NULL    COMMENT '권한 그룹 코드',
    `branch_code`    VARCHAR(8)      NULL        COMMENT '소속부서 코드',
    `job_tel_num`  VARCHAR(200)    NULL        COMMENT '사무실번호',
    `ip`           VARCHAR(20)     NULL        COMMENT '아이피',
    `ip_sub1`      VARCHAR(20)     NULL        COMMENT '아이피 서브1',
    `regist_date`       DATETIME        NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    `regist_user_no`  VARCHAR(16)     NULL        COMMENT '등록자 유저 번호',
    `modify_date`       DATETIME        NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP     COMMENT '수정 일시',
    `modify_user_no`  VARCHAR(16)     NULL        COMMENT '수정자 유저번호',
    PRIMARY KEY (user_no, agent_code),
    CONSTRAINT `employee_agent_role_mapp_user_no_fk` FOREIGN KEY (user_no) REFERENCES `employee` (user_no),
    CONSTRAINT `employee_agent_role_mapp_agent_code_fk` FOREIGN KEY (agent_code) REFERENCES `agent` (agent_code),
    CONSTRAINT `employee_agent_role_mapp_role_group_code_fk` FOREIGN KEY (role_group_code) REFERENCES `role_group` (role_group_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='직원-에이전트와 권한 매핑 테이블';

select 'employee_agent_role_mapp done';




