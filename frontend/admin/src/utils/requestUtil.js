import axios from 'axios';
// import store from '../store';

export async function createGetRequest (url, params=null) {
    // const loginInfo = store.getState().userInfoReducer.userInfo;
    // let authData = {}
    // if(loginInfo) authData = {
    //     // 'Authorization' : loginInfo.token_type.charAt(0) +' '+ loginInfo.access_token,
    //     'Authorization' : 'Bearer '+ loginInfo.access_token,
    //     'X-Requested-With': 'XMLHttpRequest'
    // };

    const result = await axios.get(url, {
        params: params,
        headers: {
            // ...authData,
            // 'Access-Control-Allow-Origin': '*',
            // 'Access-Control-Allow-Headers': 'Content-Type',
            // 'Content-Type': 'application/json',
            // "Content-Type":"application/x-www-form-urlencoded"
        },
        withCredentials: true, //for CORS
    })
    console.log(result.data);
    return result.data;
}

export async function createPostRequest (url, body=null, params=null, headers=null,) {
    // const loginInfo = store.getState().userInfoReducer.userInfo;
    // let authData = {}
    // if(loginInfo) authData = {
    //     // 'Authorization' : loginInfo.token_type.charAt(0) + ' '+ loginInfo.access_token,
    //     'Authorization' : 'Bearer '+ loginInfo.access_token,
    //     'State': loginInfo.state,
    //     'X-Requested-With': 'XMLHttpRequest'
    // };
    const result = await axios.post(url, body, {
        params: params,
        headers:{
            ...headers,
            // ...authData,
        },
        // withCredentials: true, //for CORS
    })
    return result.data;
}


export async function createPutRequest(url, body=null, params=null){
    // const loginInfo = store.getState().userInfoReducer.userInfo;
    // let authData = {}
    // if(loginInfo) authData = {
    //     'Authorization' : 'Bearer '+ loginInfo.access_token,
    //     'State': loginInfo.state,
    // };

    const result = await axios.put(url, body, {
        //config
        headers: {
            // ...authData
            // 'Access-Control-Allow-Origin': '*',
            // 'Access-Control-Allow-Methods': 'PUT, GET, POST, DELETE, OPTIONS',
            // 'Content-Type': 'application/json',
        },
        params: params,
        withCredentials: true, //for CORS
    })
    return result.data;
}

export async function createDelRequest(url, data=null, params=null){
    // const loginInfo = store.getState().userInfoReducer.userInfo;
    // let authData = {}
    // if(loginInfo) authData = {
    //     'Authorization' : 'Bearer '+ loginInfo.access_token,
    //     'State': loginInfo.state,
    // };

    const result = await axios.delete(url, {
        // withCredentials: true, //for CORS
        data:  data,
        params: params,
        headers: {
            // ...authData,
        }
    })
    return result.data;
}