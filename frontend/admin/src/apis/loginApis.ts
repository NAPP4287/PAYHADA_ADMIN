import { createPostRequest } from "utils/utilRequest";
// interface
import {
  LoginCertReqType,
  LoginMainReqType,
} from "interface/apiType/InterfaceLogin";

const baseUrl = "/api/v2";
let headers = { "Content-Type": "application/json" };

export const callLoginCheck = async () => {
  const response = await createPostRequest(`${baseUrl}/loginCheck`, null);

  return response.data;
};

export const callLogin = async (data: LoginMainReqType | LoginCertReqType) => {
  const response = await createPostRequest(
    `${baseUrl}/login`,
    data,
    null,
    headers,
  );

  return response;
};

export const callLogout = async () => {
  const response = await createPostRequest(`${baseUrl}/logout`, null);

  return response;
};
