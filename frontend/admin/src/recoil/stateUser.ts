import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";
import { UserInfoType, FailCntType } from "interface/InterfaceUser";

const { persistAtom } = recoilPersist({
  key: "importantState",
  storage: sessionStorage,
});

export const userInfoState = atom<UserInfoType>({
  key: "userInfoState",
  default: {
    id: "",
    loginId: "",
    userNo: "",
    languageCd: "ko",
    roleGroupList: [],
  },
  effects_UNSTABLE: [persistAtom],
});

export const pwdFailCntState = atom<FailCntType>({
  key: "pwdFailCntState",
  default: {
    failCnt: 0,
  },
  effects_UNSTABLE: [persistAtom],
});
