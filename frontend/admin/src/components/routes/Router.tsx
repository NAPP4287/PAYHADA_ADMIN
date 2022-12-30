import { BrowserRouter, Routes, Route } from "react-router-dom";
import Login from "pages/Login";
import Main from "pages/Main";
import PrivateRoute from "./privateRoute";

function Router() {
  return (
    <BrowserRouter>
      <Routes>
        <Route element={<PrivateRoute authentication={false} />}>
          <Route path="/login" element={<Login />} />
        </Route>
        <Route element={<PrivateRoute authentication={true} />}>
          <Route path="/main" element={<Main />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default Router;
