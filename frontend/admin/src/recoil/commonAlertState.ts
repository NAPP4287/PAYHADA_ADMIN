import { atom } from "recoil";
import { basicAlertType } from "interface/commonAlertInterface";

export const commonAlertState = atom<basicAlertType>({
  key: "commonAlertState",
  default: {
    isOpen: false,
    AlertType: "normal",
    title: "",
    content: "",
    action: undefined,
    cancel: undefined,
    buttonText: { confirm: "", cancel: "" },
  },
});
