export interface LoginMainProps {
  email: string;
  setEmail: Function;
  password: string;
  setPassword: Function;
  getLogin: Function;
}

export interface LoginCertProps {
  email: string;
  getLogin: Function;
}

export interface UserInfoType {
  id: string;
  loginId: string;
  userNo: string;
  languageCd: string;
  roleGroupList: Array<roleGroupType>;
}

export interface FailCntType {
  failCnt: number;
}

interface roleGroupType {
  roleGroupName: string;
  roleGroupCode: string;
}
export interface InvalidCheckType {
  [key: string]: RegExp;
}
