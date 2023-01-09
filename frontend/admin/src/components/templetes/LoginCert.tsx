import { useState } from "react";
import { Form, Button } from "reactstrap";
// components
import InputTimer from "components/atomic/organisms/InputTimer";
// interface
import { LoginCertProps } from "interface/InterfaceUser";

const LoginCert = (props: LoginCertProps) => {
  const { email } = props;
  const [otpInput, setOtpInput] = useState<string>("");
  const [isEnd, setIsEnd] = useState<boolean>(false);
  const [seconds, setSeconds] = useState(0);
  const [minutes, setMinutes] = useState(5);

  const onDisableButton = () => {
    if (otpInput.length !== 6 || isEnd) {
      return true;
    }
    return false;
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
          minutes={minutes}
          setMinutes={setMinutes}
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
        />
        <Button
          className="marginTop"
          block
          color="primary"
          type="button"
          disabled={onDisableButton()}>
          로그인
        </Button>
      </Form>
    </div>
  );
};

export default LoginCert;
