import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "pages/Login";
import Main from "pages/Main";
import PrivateRoute from "./PrivateRoute";

const Router = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<PrivateRoute authentication={false} />}>
          <Route path="/login" element={<Login />} />
        </Route>
        <Route element={<PrivateRoute authentication={true} />}>
          <Route path="/" element={<Main />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
};

export default Router;
