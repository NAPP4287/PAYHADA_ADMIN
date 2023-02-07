import { createPostRequest } from "utils/utilRequest";

const baseUrl = "/api/v2";
let headers = { "Content-Type": "application/json" };

export const callLogin = async (data: any) => {
  const response = await createPostRequest(
    `${baseUrl}/login`,
    data,
    null,
    headers,
  );

  return response.data;
};

export const callLogout = async () => {
  const response = await createPostRequest(`${baseUrl}/logout`, null);

  return response.data;
};
