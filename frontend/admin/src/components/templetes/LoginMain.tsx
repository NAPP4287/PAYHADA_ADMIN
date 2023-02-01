import { Form, Button } from "reactstrap";
import { useState } from "react";
// components
import LabelInput from "components/atomic/atoms/LabelInput";
// interface
import { LoginMainProps } from "interface/InterfaceUser";
import { ObjectBracketBooleanType } from "interface/InterfaceCommon";
import { BasicAlertType } from "interface/InterfaceCommonAlert";
// utils
import { invalidCheck } from "utils/utilInput";
// apis
import { callLogin } from "apis/loginApis";
// recoil
import { useRecoilState } from "recoil";
import { commonAlertState } from "recoil/stateAlert";

const LoginMain = (props: LoginMainProps) => {
  const { email, setEmail, password, setPassword, setIsLoginMain } = props;
  const [invalidData, setInvalidData] = useState<ObjectBracketBooleanType>({
    email: true,
    password: true,
  });

  const [commonAlertInfo, setCommonAlertInfo] =
    useRecoilState<BasicAlertType>(commonAlertState);

  const getLogin = async () => {
    if (handleInvaildCheck()) {
      const result = await callLogin({ id: email, pwd: password });

      if (result.resultCode !== 200) {
        setCommonAlertInfo({
          ...commonAlertInfo,
          isOpen: true,
          title: "실패",
          alertType: "error",
          content: result.resultMsg,
        });
      } else {
        setIsLoginMain(false);
      }
    }
  };

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

  return (
    <div className="alginCenter">
      <Form className="maxWidth">
        <LabelInput
          placeholder={"이메일을 입력해주세요"}
          type={"email"}
          label={"이메일"}
          setChangeData={setEmail}
          value={email}
          isFailed={invalidData.email}
          failedText={"이메일을 다시 확인해주세요"}
          onEnter={getLogin}
        />
        <LabelInput
          placeholder={"비밀번호를 입력해주세요"}
          type={"password"}
          label={"비밀번호"}
          setChangeData={setPassword}
          value={password}
          isFailed={invalidData.password}
          failedText={"비밀번호를 다시 확인해주세요"}
          onEnter={getLogin}
        />
        <Button
          block
          color="primary"
          type="button"
          className="marginTop"
          onClick={getLogin}>
          인증번호 받기
        </Button>
      </Form>
    </div>
  );
};

export default LoginMain;
