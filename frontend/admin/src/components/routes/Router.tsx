import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "pages/Login";
import DashBoard from "pages/DashBoard";
import Settings from "pages/Settings";
import PrivateRoute from "./PrivateRoute";
import NavSide from "components/layout/NavSide";
import Header from "components/layout/Header";
import { userInfoState } from "recoil/stateUser";
import { useRecoilValue } from "recoil";
import ErrorBoundary from "components/layout/ErrorBoundary";

const Router = () => {
  const userInfo = useRecoilValue(userInfoState);

  return (
    <BrowserRouter>
      {userInfo.userNo !== "" && <Header />}
      {userInfo.userNo !== "" && <NavSide />}

      <Routes>
        <Route element={<PrivateRoute authentication={false} />}>
          <Route path="/login" element={<Login />} />
        </Route>
        <Route element={<PrivateRoute authentication={true} />}>
          <Route path="/" element={<DashBoard />} />
          <Route path="/settings" element={<Settings />} />
        </Route>
        <Route path="*" element={<ErrorBoundary />} />
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
