import { Form, Button } from "reactstrap";
import { FormEvent, useState, useEffect } from "react";
// components
import LabelInput from "components/atomic/atoms/LabelInput";
import RadioButtons from "components/atomic/organisms/RadioButtons";
// interface
import { LoginMainProps } from "interface/InterfaceProps";
import { ObjectBracketBooleanType } from "interface/InterfaceCommon";
// utils
import { invalidCheck } from "utils/utilInput";
// data
import { LANGUAGE_LIST } from "data/constantsData";
// recoil
import { useRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";

const LoginMain = (props: LoginMainProps) => {
  const { email, setEmail, password, setPassword, getLogin, t, i18n } = props;
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

  useEffect(() => {
    i18n?.changeLanguage(userInfo.languageCd);
  }, [i18n, userInfo.languageCd]);

  const onSubmitLogin = (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (!handleInvaildCheck()) {
      return;
    }
    getLogin({ id: email, pwd: password });
  };

  const changeValue = (e: React.ChangeEvent<HTMLInputElement>) => {
    setUserInfo({ ...userInfo, languageCd: e.target.value });
  };

  return (
    <Form className="maxWidth" onSubmit={onSubmitLogin}>
      <RadioButtons
        radioList={LANGUAGE_LIST}
        type={"row"}
        changeValue={changeValue}
        activeValue={userInfo.languageCd}
      />
      <div className="smarginTop">
        <LabelInput
          placeholder={t("Login.emailPl")}
          type={"email"}
          label={t("Login.email")}
          setChangeData={setEmail}
          value={email}
          isFailed={invalidData.email}
          failedText={t("Login.invalidEmail")}
        />
        <LabelInput
          placeholder={t("Login.pwdPl")}
          type={"password"}
          label={t("Login.pwd")}
          setChangeData={setPassword}
          value={password}
          isFailed={invalidData.password}
          failedText={t("Login.invalidPwd")}
        />
      </div>
      <Button block color="primary" type="submit" className="marginTop">
        {t("Login.certButton")}
      </Button>
    </Form>
  );
};

export default LoginMain;
