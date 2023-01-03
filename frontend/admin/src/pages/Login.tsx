import { Form, Button } from "reactstrap";
import { useState } from "react";
import LabelInput from "components/atomic/atoms/LabelInput";
import LabelSelect from "components/atomic/atoms/LabelSelect";
import { loginInfoType, userInfoType } from "interface/userInterface";

import { useRecoilState } from "recoil";
import { userInfoState } from "recoil/userState";

function Login() {
  const [userInfo, setUserInfo] = useRecoilState<userInfoType>(userInfoState);
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [nation, setNation] = useState<string>("국가를 선택해주세요.");

  const nationSelect: Array<string> = ["한국", "호주", "미국"];

  const getLogin = () => {
    const loginData: loginInfoType = {
      email: email,
      password: password,
      nation: nation,
    };

    if (loginData.email === "") {
      alert("이메일을 입력해주세요.");
    } else if (loginData.password === "") {
      alert("비밀번호를 입력해주세요.");
    } else if (loginData.nation === "") {
      alert("국가를 선택해주세요.");
    } else {
      setUserInfo({ ...userInfo, userToken: "testUserToken" });
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
        />
        <LabelInput
          placeholder={"비밀번호를 입력해주세요"}
          type={"password"}
          label={"비밀번호"}
          setChangeData={setPassword}
          value={password}
        />
        <LabelSelect
          label={"국가선택"}
          type={"select"}
          setChangeData={setNation}
          value={nation}
          dataArray={nationSelect}
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
