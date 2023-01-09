export interface LoginMainProps {
  email: string;
  setEmail: Function;
  password: string;
  setPassword: Function;
  setIsLoginMain: Function;
}

export interface LoginCertProps {
  email: string;
}

export interface UserInfoType {
  id: string;
  userToken: string;
  userRole: string;
}

export interface InvalidCheckType {
  [key: string]: RegExp;
}
