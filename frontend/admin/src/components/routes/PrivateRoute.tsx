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

  if (authentication) {
    return !userInfo.sessionChk ? <Navigate to={"/login"} /> : <Outlet />;
  } else {
    return !userInfo.sessionChk ? <Outlet /> : <Navigate to="/" />;
  }
};

export default PrivateRoute;
