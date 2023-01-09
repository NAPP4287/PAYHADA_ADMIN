import { useState } from "react";
// components
import LoginMain from "components/templetes/LoginMain";
import LoginCert from "components/templetes/\bLoginCert";

const Login = () => {
  const [email, setEmail] = useState<string>("nanni@naver.com");
  const [password, setPassword] = useState<string>("");
  const [isLoginMain, setIsLoginMain] = useState<boolean>(false);

  return (
    <>
      {isLoginMain ? (
        <LoginMain
          email={email}
          setEmail={setEmail}
          password={password}
          setPassword={setPassword}
          setIsLoginMain={setIsLoginMain}
        />
      ) : (
        <LoginCert email={email} />
      )}
    </>
  );
};

export default Login;
