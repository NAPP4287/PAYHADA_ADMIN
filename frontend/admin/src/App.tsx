import { useEffect } from "react";
// css
import "assets/css/common.css";
import "assets/argon/plugins/nucleo/css/nucleo.css";
import "assets/argon/css/argon-dashboard-react.min.css";
// components
import Router from "components/routes/Router";
import BasicAlert from "components/atomic/atoms/AlertBasic";
// recoil
import { useRecoilValue } from "recoil";
import { commonAlertState } from "recoil/stateAlert";
import { userInfoState } from "recoil/stateUser";
// i18n
import { useTranslation } from "react-i18next";

const App = () => {
  const commonAlertInfo = useRecoilValue(commonAlertState);
  const userInfo = useRecoilValue(userInfoState);

  const { i18n } = useTranslation();

  useEffect(() => {
    i18n.changeLanguage(userInfo.languageCd);
  }, [i18n, userInfo.languageCd]);

  return (
    <div style={{ maxWidth: "1980px" }}>
      <Router />
      {commonAlertInfo.isOpen && <BasicAlert />}
    </div>
  );
};

export default App;
