import { useState } from "react";
// components
import LoginMain from "components/templetes/LoginMain";
import LoginCert from "components/templetes/LoginCert";

const Login = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [isLoginMain, setIsLoginMain] = useState<boolean>(true);

  return (
    <div className="alginCenter">
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
    </div>
  );
};

export default Login;
