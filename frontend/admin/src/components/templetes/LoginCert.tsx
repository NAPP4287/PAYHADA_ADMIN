import { useState, FormEvent } from "react";
import { Form, Button } from "reactstrap";
// components
import InputTimer from "components/atomic/organisms/InputTimer";
// interface
import { LoginCertProps } from "interface/InterfaceUser";
// recoil
import { useRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";
// apis
import { callLogin } from "apis/loginApis";

const LoginCert = (props: LoginCertProps) => {
  const { email } = props;
  const [otpInput, setOtpInput] = useState<string>("");
  const [isEnd, setIsEnd] = useState<boolean>(false);
  const [seconds, setSeconds] = useState(300);

  const [userInfo, setUserInfo] = useRecoilState(userInfoState);

  const onCheckOtp = () => {
    if (otpInput.length !== 6 || isEnd) {
      return false;
    }
    return true;
  };

  const getOtpChk = async () => {
    // OTP API 요청
    const result = await callLogin({ secret: otpInput });
    console.log(result);
    if (result.resultCode === "E2005") {
      setUserInfo({ ...userInfo, userNo: result.data.userNo });
    }
  };

  const onSubmitOtp = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!onCheckOtp()) {
      return;
    }
    getOtpChk();
  };

  return (
    <div className="alginCenter">
      <Form className="maxWidth" onSubmit={onSubmitOtp}>
        <span>아래 이메일로 인증번호를 전송하였습니다.</span>
        <p className="primary" style={{ fontWeight: "bold" }}>
          {email}
        </p>
        <InputTimer
          seconds={seconds}
          setSeconds={setSeconds}
          setIsEnd={setIsEnd}
          isEnd={isEnd}
          placeholder={"인증번호를 입력해주세요"}
          type={"default"}
          label={"인증번호"}
          setChangeData={setOtpInput}
          value={otpInput}
          maxLength={6}
          isFailed={true}
          resetTime={300}
        />
        <Button
          className="marginTop"
          block
          type="submit"
          color="primary"
          disabled={otpInput.length < 6 || isEnd}>
          로그인
        </Button>
      </Form>
    </div>
  );
};

export default LoginCert;
