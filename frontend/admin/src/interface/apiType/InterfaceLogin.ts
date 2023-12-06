import { roleGroupType } from "interface/InterfaceUser";

export interface LoginMainReqType {
  id: string;
  pwd: string;
}

export interface LoginCertReqType {
  secret: string;
}

export interface LoginCertResType {
  id: string;
  userNo: string;
  loginId: string;
  languageCd: string;
  roleGroupList: Array<roleGroupType>;
  sessionChk: boolean;
}
