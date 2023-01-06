import { Form, Button } from "reactstrap";
import { useState } from "react";
// components
import LabelInput from "components/atomic/atoms/LabelInput";
import LabelSelect from "components/atomic/atoms/LabelSelect";
// interface
import { UserInfoType } from "interface/InterfaceUser";
import { ObjectBracketBooleanType } from "interface/InterfaceCommon";
// reciol
import { useRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";
// utils
import { invalidCheck } from "utils/utilInput";

function Login() {
  const nationSelect: Array<string> = ["한국", "호주", "미국"];
  const [userInfo, setUserInfo] = useRecoilState<UserInfoType>(userInfoState);

  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [nation, setNation] = useState<string>("");

  const [invalidData, setInvalidData] = useState<ObjectBracketBooleanType>({
    email: true,
    password: true,
    nation: true,
  });

  const getLogin = () => {
    handleInvaildCheck() &&
      setUserInfo({ ...userInfo, userToken: "userToken" });
  };

  const handleInvaildCheck = () => {
    const invalid = {
      email: invalidCheck("email", email),
      password: invalidCheck("password", password),
      nation: !invalidCheck("nation", nation),
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
        <LabelSelect
          label={"국가선택"}
          type={"select"}
          setChangeData={setNation}
          value={nation}
          dataArray={nationSelect}
          isFailed={!invalidData.nation}
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
