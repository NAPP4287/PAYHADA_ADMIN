import axios from "axios";
// recoil
import { commonAlertState } from "recoil/stateAlert";
import { getRecoil, setRecoil } from "recoil-nexus";

const instance = axios.create();

const returnToLogin = () => {
  window.location.replace("/");
};

// 요청 인터셉터 추가
instance.interceptors.request.use(
  (config) => {
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

// 응답 인터셉터 추가
instance.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    const commonAlertInfo = getRecoil(commonAlertState);

    if (error.response.data.resultCode === "E2000") {
      setRecoil(commonAlertState, {
        ...commonAlertInfo,
        isOpen: true,
        title: "Error",
        alertType: "error",
        content: error.response.data.resultMsg,
        action: returnToLogin,
      });

      return localStorage.setItem("session", "logout");
    }

    if (error.response.data.resultMsg !== undefined) {
      setRecoil(commonAlertState, {
        ...commonAlertInfo,
        isOpen: true,
        title: "Error",
        alertType: "error",
        content: error.response.data.resultMsg,
      });
    } else {
      setRecoil(commonAlertState, {
        ...commonAlertInfo,
        isOpen: true,
        title: "Error",
        alertType: "error",
        content: error.response.statusText,
      });
    }
  },
);

export const createGetRequest = () => {};

export const createPostRequest = async (
  url: string,
  body: object | null,
  params?: string | null,
  headers?: object | null,
) => {
  const result = await instance.post(url, body, {
    params: params,
    headers: {
      ...headers,
      "Access-Control-Allow-Origin": "*",
    },
    withCredentials: true,
  });
  return result.data;
};

export const createPutRequest = async (
  url: string,
  body: object,
  params?: string | null,
) => {
  const result = await instance.put(url, body, {
    headers: {},
    params: params,
  });

  return result.data;
};
