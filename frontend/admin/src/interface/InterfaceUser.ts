import { ITranslation } from "./InterfaceCommon";
export interface LoginMainProps extends ITranslation {
  email: string;
  setEmail: Function;
  password: string;
  setPassword: Function;
  getLogin: Function;
}

export interface LoginCertProps extends ITranslation {
  email: string;
  getLogin: Function;
}

export interface UserInfoType {
  id: string;
  loginId: string;
  userNo: string;
  languageCd: string;
  roleGroupList: Array<roleGroupType>;
  sessionChk: boolean;
}

export interface FailCntType {
  failCnt: number;
}

export interface roleGroupType {
  roleGroupName: string;
  roleGroupCode: string;
}
export interface InvalidCheckType {
  [key: string]: RegExp;
}
