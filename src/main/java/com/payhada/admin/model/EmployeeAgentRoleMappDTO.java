package com.payhada.admin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 직원이 속한 에이전트 와 에이전트 내에서의 권한 매핑 객체
 */
@Getter
@NoArgsConstructor
@ToString
public class EmployeeAgentRoleMappDTO {
    private String userNo;              // 유저번호
    private String agentCode;           // 유저가 속한 에이전트 코드
    private String nationCode;          // 국가코드 (해당 에이전트의 국가코드)
    private String roleGroupCode;       // 에이전트 내의 권한그룹코드
    private String roleGroupName;       // 권한그룹명
    private String branchCode;          // 소속 부서 코드
    private String jobTelNum;           // 직장 번호
    private String ip;                  // 유저 아이피
    private String ipSub1;              // 유저 아이피 (서브)
    private String registDate;          // 해당 에이전트에 소속된 날짜
    private String modifyDate;          // 해당 에이전트의 권한, 연락처 등 정보가 변경된 날짜
}
