import "assets/css/App.css";
import "assets/css/common.css";
import "assets/vendor/nucleo/css/nucleo.css";
import "assets/vendor/font-awesome/css/font-awesome.min.css";
import "assets/css/argon-design-system-react.min.css";

import Router from "components/routes/Router";
import BasicAlert from "components/atomic/atoms/BasicAlert";

import { useRecoilValue } from "recoil";
import { commonAlertState } from "recoil/commonAlertState";

function App() {
  const commonAlertInfo = useRecoilValue(commonAlertState);

  return (
    <div className="App">
      <Router />
      {commonAlertInfo.isOpen && <BasicAlert />}
    </div>
  );
}

export default App;
