import { createPostRequest } from "utils/utilRequest";
import { ChangeLangReqType } from "interface/apiType/InterfaceSetting";

const baseUrl = "/api/v2";

export const changeLanguage = async (data: ChangeLangReqType) => {
  const response = await createPostRequest(`${baseUrl}/language`, data);

  return response;
};
