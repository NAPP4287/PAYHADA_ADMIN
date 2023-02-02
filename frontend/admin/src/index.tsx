import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { RecoilRoot } from "recoil";
import RecoilNexus from "recoil-nexus";
import "bootstrap/dist/css/bootstrap.css";
import "assets/css/index.css";

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
