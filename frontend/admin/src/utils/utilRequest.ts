import axios from "axios";

const instance = axios.create();

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
    return Promise.reject(error);
  },
);

export const createGetRequest = () => {};

export const createPostRequest = async (
  url: string,
  body: string | null,
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
  return result;
};
