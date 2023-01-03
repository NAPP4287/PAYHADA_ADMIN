import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";
import { userInfoType } from "interface/userInterface";

const { persistAtom } = recoilPersist({
  key: "importantState",
  storage: sessionStorage,
});

export const userInfoState = atom<userInfoType>({
  key: "userInfoState",
  default: {
    id: "",
    userToken: "",
    userRole: "master",
  },
  effects_UNSTABLE: [persistAtom],
});
