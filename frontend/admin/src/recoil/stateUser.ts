import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";
import { UserInfoType } from "interface/InterfaceUser";

const { persistAtom } = recoilPersist({
  key: "importantState",
  storage: sessionStorage,
});

export const userInfoState = atom<UserInfoType>({
  key: "userInfoState",
  default: {
    id: "",
    userToken: "",
    userRole: "master",
  },
  effects_UNSTABLE: [persistAtom],
});
