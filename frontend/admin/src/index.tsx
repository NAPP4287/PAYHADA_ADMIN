import ReactDOM from "react-dom/client";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
// recoil
import { RecoilRoot } from "recoil";
import RecoilNexus from "recoil-nexus";
// css
import "bootstrap/dist/css/bootstrap.css";
import "assets/css/index.css";
// i18n
import "i18n/index";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement,
);

root.render(
  <RecoilRoot>
    <RecoilNexus />
    <App />
  </RecoilRoot>,
);

reportWebVitals();
