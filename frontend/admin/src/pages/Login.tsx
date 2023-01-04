import { Form, Button } from "reactstrap";
import { useState } from "react";
// component
import LabelInput from "components/atomic/atoms/LabelInput";
import LabelSelect from "components/atomic/atoms/LabelSelect";
// interface
import { LoginInfoType, UserInfoType } from "interface/InterfaceUser";
import {
  ObjectBracketStringType,
  ObjectBracketBooleanType,
} from "interface/InterfaceCommon";
// reciol
import { useRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";
//util
import { invalidCheck } from "utils/utilInput";

function Login() {
  const nationSelect: Array<string> = ["한국", "호주", "미국"];
  const [userInfo, setUserInfo] = useRecoilState<UserInfoType>(userInfoState);
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [nation, setNation] = useState<string>("국가를 선택해주세요");
  const [errorSelect, setErrorSelect] = useState<boolean>(false);
  const [errorInput, setErrorInput] = useState<ObjectBracketBooleanType>({
    email: false,
    password: false,
  });

  const getLogin = () => {
    handleInvaildCheck();
  };

  const handleInvaildCheck = () => {
    const inputData: ObjectBracketStringType = {
      email: email,
      password: password,
    };
    const invalidData = invalidCheck(inputData);
    const changeError: ObjectBracketBooleanType = {
      email: false,
      password: false,
    };
    invalidData.map((el) => (changeError[el] = true));
    setErrorInput({ ...changeError });

    if (nation === "국가를 선택해주세요") {
      setErrorSelect(true);
    } else {
      setErrorSelect(false);
    }
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
          isFailed={errorInput["email"]}
          failedText={"이메일을 다시 확인해주세요"}
        />
        <LabelInput
          placeholder={"비밀번호를 입력해주세요"}
          type={"password"}
          label={"비밀번호"}
          setChangeData={setPassword}
          value={password}
          isFailed={errorInput["password"]}
          failedText={"비밀번호를 다시 확인해주세요"}
        />
        <LabelSelect
          label={"국가선택"}
          type={"select"}
          setChangeData={setNation}
          value={nation}
          dataArray={nationSelect}
          isFailed={errorSelect}
          failedText={"국가를 선택해주세요"}
        />
        <Button
          block
          color="primary"
          type="button"
          className="marginTop"
          onClick={getLogin}>
          로그인
        </Button>
      </Form>
    </div>
  );
}

export default Login;
