import { atom } from "recoil";
import { BasicAlertType } from "interface/InterfaceCommonAlert";

export const commonAlertState = atom<BasicAlertType>({
  key: "commonAlertState",
  default: {
    isOpen: false,
    AlertType: "normal",
    title: "",
    content: "",
    action: undefined,
    cancel: undefined,
    buttonText: { confirm: "확인", cancel: "취소" },
  },
});
