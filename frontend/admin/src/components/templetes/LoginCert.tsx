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
// router
import { useNavigate } from "react-router-dom";

const LoginCert = (props: LoginCertProps) => {
  const { email, getLogin, t, i18n } = props;
  const [otpInput, setOtpInput] = useState<string>("");
  const [isEnd, setIsEnd] = useState<boolean>(false);
  const [seconds, setSeconds] = useState(300);

  const [userInfo, setUserInfo] = useRecoilState(userInfoState);

  const navigate = useNavigate();

  const onCheckOtp = () => {
    if (otpInput.length !== 6 || isEnd) {
      return false;
    }
    return true;
  };

  const getOtpChk = async () => {
    // OTP API 요청
    const result = await callLogin({ secret: otpInput });
    if (result.resultCode === "E2005") {
      const data = result.data;
      setUserInfo({
        ...userInfo,
        id: data.id,
        userNo: data.userNo,
        loginId: data.loginId,
        languageCd: data.languageCd,
        roleGroupList: data.roleGroupList,
        sessionChk: true,
      });
      navigate("/");
      i18n?.changeLanguage(result.data.languageCd);
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
    <Form className="maxWidth" onSubmit={onSubmitOtp}>
      <span>{t("Login.certText")}</span>
      <p className="primary" style={{ fontWeight: "bold" }}>
        {email}
      </p>
      <InputTimer
        seconds={seconds}
        setSeconds={setSeconds}
        setIsEnd={setIsEnd}
        placeholder={t("Login.certPl")}
        type={"default"}
        label={t("Login.certNum")}
        setChangeData={setOtpInput}
        value={otpInput}
        maxLength={6}
        resetTime={300}
        isEnd={isEnd}
        actionFunc={getLogin}
      />
      <Button
        className="marginTop"
        block
        type="submit"
        color="primary"
        disabled={otpInput.length < 6 || isEnd}>
        {t("Login.login")}
      </Button>
    </Form>
  );
};

export default LoginCert;
