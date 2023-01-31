package com.payhada.admin.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class EmployeeInfoDTO {
    private String userNo;
    private String userName;
    private String firstNameEn;
    private String lastNameEn;
    private String email;
    private String telNum;
    private String mobileNum;
    private String gender;
    private String birth;
    private String nationalityCode;
    private String zipCode;
    private String address;
    private String addressDetail;
    private String addressEn;
    private String addressDetailEn;
    private String city;
    private String state;
    private String joinDate;
    private String leaveDate;
    private String modifyDate;
    private String modifyUserNo;

    @Builder
    public EmployeeInfoDTO(String userNo, String userName, String firstNameEn, String lastNameEn, String email, String telNum,
                           String mobileNum, String gender, String birth, String nationalityCode, String zipCode, String address,
                           String addressDetail, String addressEn, String addressDetailEn, String city, String state,
                           String joinDate, String leaveDate, String modifyDate, String modifyUserNo) {
        this.userNo = userNo;
        this.userName = userName;
        this.firstNameEn = firstNameEn;
        this.lastNameEn = lastNameEn;
        this.email = email;
        this.telNum = telNum;
        this.mobileNum = mobileNum;
        this.gender = gender;
        this.birth = birth;
        this.nationalityCode = nationalityCode;
        this.zipCode = zipCode;
        this.address = address;
        this.addressDetail = addressDetail;
        this.addressEn = addressEn;
        this.addressDetailEn = addressDetailEn;
        this.city = city;
        this.state = state;
        this.joinDate = joinDate;
        this.leaveDate = leaveDate;
        this.modifyDate = modifyDate;
        this.modifyUserNo = modifyUserNo;
    }
}
