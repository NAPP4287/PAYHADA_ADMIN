import i18n from "i18next";
import { initReactI18next } from "react-i18next";

import En from "./en.json";
import Ko from "./ko.json";

const resouce = {
  en: {
    translation: En,
  },
  ko: {
    translation: Ko,
  },
};

i18n.use(initReactI18next).init({
  resources: resouce,
  lng: "ko",
  fallbackLng: ["ko", "en"],
  debug: true,
  keySeparator: ".",
  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
