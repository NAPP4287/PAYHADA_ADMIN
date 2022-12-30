import { Button, Input } from "reactstrap";
import { useRecoilState } from "recoil";
import { userInfoState, userInfoType } from "recoil/userState";

function Login() {
  const [userInfo, setUserInfo] = useRecoilState<userInfoType>(userInfoState);

  return (
    <div>
      <Input></Input>
      <Button
        color="danger"
        onClick={() =>
          setUserInfo({ ...userInfo, userToken: "user" })
        }>{`${userInfo.userRole}`}</Button>
    </div>
  );
}

export default Login;
