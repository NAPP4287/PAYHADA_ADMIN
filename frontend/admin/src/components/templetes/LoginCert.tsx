import { useState } from "react";
import { Form, Button } from "reactstrap";
// components
import InputTimer from "components/atomic/organisms/InputTimer";
// interface
import { LoginCertProps } from "interface/InterfaceUser";
import { BasicAlertType } from "interface/InterfaceCommonAlert";
// recoil
import { useRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";
import { commonAlertState } from "recoil/stateAlert";
// apis
import { callLogin } from "apis/loginApis";

const LoginCert = (props: LoginCertProps) => {
  const { email } = props;
  const [otpInput, setOtpInput] = useState<string>("");
  const [isEnd, setIsEnd] = useState<boolean>(false);
  const [seconds, setSeconds] = useState(300);

  const [userInfo, setUserInfo] = useRecoilState(userInfoState);
  const [commonAlertInfo, setCommonAlertInfo] =
    useRecoilState<BasicAlertType>(commonAlertState);

  const onCheckOtp = () => {
    if (otpInput.length !== 6 || isEnd) {
      return false;
    }
    return true;
  };

  const onClickCert = async () => {
    // OTP API 요청
    if (onCheckOtp()) {
      const result = await callLogin({ secret: otpInput });
      if (result.resultCode !== 200) {
        setCommonAlertInfo({
          ...commonAlertInfo,
          isOpen: true,
          title: "실패",
          alertType: "error",
          content: result.resultMsg,
        });
      } else {
        setUserInfo({ ...userInfo, userNo: result.data.userNo });
      }
    }
  };

  return (
    <div className="alginCenter">
      <Form className="maxWidth">
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
          onEnter={onClickCert}
          resetTime={300}
        />
        <Button
          className="marginTop"
          block
          color="primary"
          onClick={onClickCert}>
          로그인
        </Button>
      </Form>
    </div>
  );
};

export default LoginCert;
