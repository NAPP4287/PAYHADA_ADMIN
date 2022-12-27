import { createGetRequest, createPostRequest, createPutRequest, createDelReques } from '../utils/requestUtil';
import axios from 'axios';

const baseUrl = '/api/v2'

// 로그인 처리
export const postLogin = async (body) => {
    const response = await createPostRequest(`${baseUrl}/login`, body)
    return response;
};

// For Test
export const getTest = async () => {
    const response = await createGetRequest(`${baseUrl}/test`)
    return response;
};