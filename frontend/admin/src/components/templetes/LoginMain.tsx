import { Form, Button } from "reactstrap";
import { FormEvent, useState } from "react";
// components
import LabelInput from "components/atomic/atoms/LabelInput";
import RadioButtons from "components/atomic/organisms/RadioButtons";
// interface
import { LoginMainProps } from "interface/InterfaceUser";
import { ObjectBracketBooleanType } from "interface/InterfaceCommon";
// utils
import { invalidCheck } from "utils/utilInput";
// apis
import { callLogin } from "apis/loginApis";
// data
import { languageList } from "data/radioCheckList";
// recoil
import { useRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";

const LoginMain = (props: LoginMainProps) => {
  const { email, setEmail, password, setPassword, setIsLoginMain } = props;
  const [invalidData, setInvalidData] = useState<ObjectBracketBooleanType>({
    email: true,
    password: true,
  });

  const [userInfo, setUserInfo] = useRecoilState(userInfoState);

  const handleInvaildCheck = () => {
    const invalid = {
      email: invalidCheck("email", email),
      password: invalidCheck("password", password),
    };

    const valueArr = Object.values(invalid);
    const findValue = valueArr.find((e) => e === false);
    setInvalidData({ ...invalid });

    return findValue === undefined;
  };

  const getLogin = async () => {
    const result = await callLogin({ id: email, pwd: password });

    if (result.resultCode === "E2004") {
      setIsLoginMain(false);
    }
  };

  const onSubmitLogin = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!handleInvaildCheck()) {
      return;
    }
    getLogin();
  };

  const changeValue = (e: any) => {
    setUserInfo({ ...userInfo, languageCd: e.target.value });
  };

  return (
    <Form className="maxWidth" onSubmit={onSubmitLogin}>
      <RadioButtons
        radioList={languageList}
        type={"row"}
        changeValue={changeValue}
      />
      <div className="smarginTop">
        <LabelInput
          placeholder={"이메일을 입력해주세요"}
          type={"email"}
          label={"이메일"}
          setChangeData={setEmail}
          value={email}
          isFailed={invalidData.email}
          failedText={"이메일을 다시 확인해주세요"}
        />
        <LabelInput
          placeholder={"비밀번호를 입력해주세요"}
          type={"password"}
          label={"비밀번호"}
          setChangeData={setPassword}
          value={password}
          isFailed={invalidData.password}
          failedText={"비밀번호를 다시 확인해주세요"}
        />
      </div>
      <Button block color="primary" type="submit" className="marginTop">
        인증번호 받기
      </Button>
    </Form>
  );
};

export default LoginMain;
