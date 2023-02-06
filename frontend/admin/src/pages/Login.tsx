import { useState } from "react";
// components
import LoginMain from "components/templetes/LoginMain";
import LoginCert from "components/templetes/LoginCert";
// apis
import { callLogin } from "apis/loginApis";

const Login = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [isLoginMain, setIsLoginMain] = useState<boolean>(true);

  const getLogin = async () => {
    const result = await callLogin({ id: email, pwd: password });

    if (result.resultCode === "E2004") {
      setIsLoginMain(false);
    }
  };

  return (
    <div className="alginCenter">
      {isLoginMain ? (
        <LoginMain
          email={email}
          setEmail={setEmail}
          password={password}
          setPassword={setPassword}
          getLogin={getLogin}
        />
      ) : (
        <LoginCert email={email} getLogin={getLogin} />
      )}
    </div>
  );
};

export default Login;
