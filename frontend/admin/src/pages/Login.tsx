import { useState } from "react";
// components
import LoginMain from "components/templetes/LoginMain";
import LoginCert from "components/templetes/LoginCert";
// apis
import { callLogin } from "apis/loginApis";
// i18n
import { useTranslation } from "react-i18next";
import Loading from "components/atomic/atoms/Loading";

const Login = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [isLoginMain, setIsLoginMain] = useState<boolean>(true);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const [t, i18n] = useTranslation();

  const getLogin = async () => {
    setIsLoading(true);
    const result = await callLogin({ id: email, pwd: password });
    if (result.resultCode === "E2004") {
      setIsLoginMain(false);
      setIsLoading(false);
    }
  };

  return (
    <>
      {isLoading ? (
        <Loading />
      ) : (
        <div className="alginCenter">
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
