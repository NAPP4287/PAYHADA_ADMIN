import "assets/css/common.css";
import "assets/argon/plugins/nucleo/css/nucleo.css";
import "assets/argon/css/argon-dashboard-react.min.css";

import Router from "components/routes/Router";
import BasicAlert from "components/atomic/atoms/AlertBasic";

import { useRecoilValue } from "recoil";
import { commonAlertState } from "recoil/stateAlert";

const App = () => {
  const commonAlertInfo = useRecoilValue(commonAlertState);

  return (
    <div className="App">
      <Router />
      {commonAlertInfo.isOpen && <BasicAlert />}
    </div>
  );
};

export default App;
