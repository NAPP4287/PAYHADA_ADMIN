import { useState, useEffect } from "react";
// components
import LoginMain from "components/templetes/Login/LoginMain";
import LoginCert from "components/templetes/Login/LoginCert";
// apis
import { callLogin, callLoginCheck } from "apis/loginApis";
// i18n
import { useTranslation } from "react-i18next";
import Loading from "components/atomic/atoms/Loading";
// interfaces
import { LoginMainDataType, LoginCertDataType } from "interface/InterfaceUser";
// recoil
import { useResetRecoilState, useRecoilState } from "recoil";
import { userInfoState } from "recoil/stateUser";

const Login = () => {
  const location = window.location;
  const localCheck = localStorage.getItem("session");

  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [isLoginMain, setIsLoginMain] = useState<boolean>(true);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const [t, i18n] = useTranslation();

  const resetUserInfo = useResetRecoilState(userInfoState);
  const [userInfo, setUserInfo] = useRecoilState(userInfoState);

  const getLogin = async (data: LoginMainDataType | LoginCertDataType) => {
    setIsLoading(true);
    const result = await callLogin(data);
    if (result?.resultCode === "S0000") {
      setIsLoginMain(false);
    }
    setIsLoading(false);
  };

  const loginCheck = async () => {
    const result = await callLoginCheck();

    if (result.resultCode === "S0000") {
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
    } else {
      resetUserInfo();
    }
  };

  useEffect(() => {
    if (location.pathname === "/login" && localCheck === "login") {
      loginCheck();
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <>
      {isLoading ? (
        <Loading />
      ) : (
        <div className="alignCenter" style={{ height: "100vh" }}>
          {isLoginMain ? (
            <LoginMain
              email={email}
              setEmail={setEmail}
              password={password}
              setPassword={setPassword}
              getLogin={getLogin}
              t={t}
              i18n={i18n}
            />
          ) : (
            <LoginCert email={email} getLogin={getLogin} t={t} i18n={i18n} />
          )}
        </div>
      )}
    </>
  );
};

export default Login;
