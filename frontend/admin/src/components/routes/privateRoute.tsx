import { ReactElement } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { userInfoState } from "recoil/userState";

interface PrivateRouteProps {
  children?: ReactElement;
  authentication: boolean;
}

function PrivateRoute({ authentication }: PrivateRouteProps) {
  const userInfo = useRecoilValue(userInfoState);

  const changeUserInfo =
    sessionStorage.length !== 0
      ? JSON.parse(sessionStorage.importantState)?.userInfoState
      : userInfo;

  if (authentication) {
    return changeUserInfo.userToken === "" ? (
      <Navigate to={"/login"} />
    ) : (
      <Outlet />
    );
  } else {
    return changeUserInfo.userToken === "" ? (
      <Outlet />
    ) : (
      <Navigate to="/main" />
    );
  }
}

export default PrivateRoute;
