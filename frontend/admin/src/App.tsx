import { useEffect } from "react";
// css
import "assets/css/common.css";
import "assets/argon/plugins/nucleo/css/nucleo.css";
import "assets/argon/css/argon-dashboard-react.min.css";
// components
import Router from "components/routes/Router";
import BasicAlert from "components/atomic/atoms/AlertBasic";
// recoil
import { useRecoilValue, useResetRecoilState } from "recoil";
import { commonAlertState } from "recoil/stateAlert";
import { userInfoState } from "recoil/stateUser";
// i18n
import { useTranslation } from "react-i18next";
// apis
import { callLoginCheck } from "apis/loginApis";

const App = () => {
  const location = window.location;
  const commonAlertInfo = useRecoilValue(commonAlertState);
  const userInfo = useRecoilValue(userInfoState);
  const resetUserInfo = useResetRecoilState(userInfoState);

  const { i18n } = useTranslation();

  const loginCheck = async () => {
    const result = await callLoginCheck();
    if (result.resultCode !== "E0000") {
      resetUserInfo();
    }
  };

  useEffect(() => {
    if (location.pathname !== "/login") loginCheck();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    i18n.changeLanguage(userInfo.languageCd);
  }, [i18n, userInfo.languageCd]);

  return (
    <div className="App">
      <Router />
      {commonAlertInfo.isOpen && <BasicAlert />}
    </div>
  );
};

export default App;
