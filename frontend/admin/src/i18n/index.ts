import i18n, { Resource } from "i18next";
import { initReactI18next } from "react-i18next";

import * as En from "./en.json";
import * as Ko from "./ko.json";

const resources: Resource = {
  en: { translation: En },
  ko: { translation: Ko },
} as const;

i18n.use(initReactI18next).init({
  resources: resources,
  lng: "ko",
  fallbackLng: {
    en: ["en"], // 한국어 불러오는 것이 실패했을 경우 영문으로 불러오기
    default: ["ko"],
  },
  debug: true,
  keySeparator: ".",
  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
