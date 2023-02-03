import { ReactElement } from "react";
import { Navigate, Outlet } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { userInfoState } from "recoil/stateUser";

interface PrivateRouteProps {
  children?: ReactElement;
  authentication: boolean;
}

const PrivateRoute = ({ authentication }: PrivateRouteProps) => {
  const userInfo = useRecoilValue(userInfoState);

  const changeUserInfo =
    sessionStorage.length !== 0
      ? JSON.parse(sessionStorage.importantState)?.userInfoState
      : userInfo;

  if (authentication) {
    return changeUserInfo.userNo === "" ? (
      <Navigate to={"/login"} />
    ) : (
      <Outlet />
    );
  } else {
    return changeUserInfo.userNo === "" ? <Outlet /> : <Navigate to="/" />;
  }
};

export default PrivateRoute;
