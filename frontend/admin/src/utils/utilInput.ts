import { InvalidCheckType } from "interface/InterfaceUser";
import { ObjectBracketStringType } from "interface/InterfaceCommon";

const invalidRule: InvalidCheckType = {
  // eslint-disable-next-line
  email: /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/, // eslint-disable-next-line
  password: /^[A-Za-z0-9`~!@#\$%\^&\*\(\)\{\}\[\]\-_=\+\\|;:'"<>,\./\?]{8,20}$/,
};
// 비밀번호 같은경우, 8~20자 사이에 길이를 갖고 있고 영문 대소문자, 숫자, 정규식에 나열되어 있는 특수문자에 포함

export const invalidCheck = (inputData: ObjectBracketStringType) => {
  const inputDataKeys = Object.keys(inputData);

  return inputDataKeys.filter(
    (el) => !invalidRule[el].test(inputData[el]) && el,
  );
};
