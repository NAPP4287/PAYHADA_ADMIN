import { createPutRequest } from "utils/utilRequest";
import { ChangeLangDataType } from "interface/InterfaceUser";

const baseUrl = "/api/v2";

export const changeLanguage = async (data: ChangeLangDataType) => {
  const response = await createPutRequest(`${baseUrl}/language`, data);

  return response.data;
};
