export interface LoginInfoType {
  email: string;
  password: string;
  nation: string;
}

export interface UserInfoType {
  id: string;
  userToken: string;
  userRole: string;
}

export interface InvalidCheckType {
  [key: string]: RegExp;
}
