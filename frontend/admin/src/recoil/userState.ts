import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist({
  key: "importantState",
  storage: sessionStorage,
});

export interface userInfoType {
  id: string;
  userToken: string;
  userRole: string;
}

export const userInfoState = atom<userInfoType>({
  key: "userInfoState",
  default: {
    id: "",
    userToken: "",
    userRole: "master",
  },
  effects_UNSTABLE: [persistAtom],
});
