package com.payhada.admin.common.util;

import com.google.gson.Gson;
import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpUtils {

    private static final String ERROR_LOG = "[HttpUtils - {}] :: {} - {}";

    /**
     * 응답 Http Status Code 가 200번대가 아닐 경우에도 바디 값을 받기 위해 ResponseHandler 설정
     * 응답 Header, Body 사용을 편하게 하기 위해 ResponseEntity 객체로 반환
     */
    private static final ResponseHandler<ResponseEntity<String>> RESPONSE_HANDLER = new ResponseHandler<ResponseEntity<String>>() {
        @Override
        public ResponseEntity<String> handleResponse(HttpResponse response) throws IOException {
            int responseStatusCode = response.getStatusLine().getStatusCode();

            String responseBody;
            if (responseStatusCode >= 200 && responseStatusCode < 300) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                responseBody = handler.handleResponse(response);
            } else {
                HttpEntity entity = response.getEntity();
                responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }

            HttpHeaders httpHeaders = new HttpHeaders();
            for (Header header : response.getAllHeaders()) {
                httpHeaders.add(header.getName(), header.getValue());
            }

            return ResponseEntity
                    .status(responseStatusCode)
                    .headers(httpHeaders)
                    .body(responseBody);
        }
    };

    /**
     * 요청 파라미터를 Query String 으로 만들어 Http GET 요청
     * @param url        요청 url
     * @param header     요청 헤더
     * @param param      요청 파라미터
     * @param timeoutSec timeout 시간
     */
    public static ResponseEntity<String> sendHttpGet(String url, Map<String, String> header, Map<String, String> param, Integer timeoutSec) throws BusinessException {
        // timeout 설정을 위해 RequestConfig 객체 생성
        RequestConfig requestConfig = getRequestConfig(timeoutSec);

        try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build()) {
            // Query Param 생성
            List<NameValuePair> nameValuePairs = convertParam(param);
            URI uri = new URIBuilder(url)
                    .addParameters(nameValuePairs)
                    .build();

            HttpGet httpGet = new HttpGet(uri);

            // 헤더값 추가
            header.forEach(httpGet::setHeader);

            // 요청 전 로깅
            log.debug("Request Method :: {}", "GET");
            log.debug("Request URI :: {}", uri.toString());
            log.debug("Request Header :: {}", header);

            // 요청
            ResponseEntity<String> responseEntity = client.execute(httpGet, RESPONSE_HANDLER);

            // 요청 후 로깅
            log.debug("Response Status Code :: {}", responseEntity.getStatusCodeValue());
            log.debug("Response Header :: {}", responseEntity.getHeaders());
            log.debug("Response Body :: {}", responseEntity.getBody());

            return responseEntity;
        } catch (Exception e) {
            log.error(ERROR_LOG, "sendHttpGet()", e.getClass().getName(), e.getMessage());

            throw new BusinessException(ResponseCode.HTTP_REQUEST_ERROR);
        }
    }

    /**
     * application/json 으로 Http POST 요청
     * @param uri        요청 uri
     * @param header     요청 헤더
     * @param body       요청 바디
     * @param timeoutSec timeout 시간
     */
    public static ResponseEntity<String> sendHttpPost(String uri, Map<String, String> header, Map<String, Object> body, Integer timeoutSec) throws BusinessException {
        // timeout 설정을 위해 RequestConfig 객체 생성
        RequestConfig requestConfig = getRequestConfig(timeoutSec);

        try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build()) {
            HttpPost httpPost = new HttpPost(uri);

            // 헤더값 추가
            header.forEach(httpPost::setHeader);
            httpPost.setHeader("Content-type", ContentType.APPLICATION_JSON.toString());

            // 바디값 추가
            Gson gson = new Gson();
            String requestBodyStr = gson.toJson(body);
            StringEntity stringEntity = new StringEntity(requestBodyStr);
            httpPost.setEntity(stringEntity);

            // 요청 전 로깅
            log.debug("Request Method :: {}", "POST");
            log.debug("Request URI :: {}", uri);
            log.debug("Request Header :: {}", header);
            log.debug("Request Body :: {}", body.toString());

            // 요청
            ResponseEntity<String> responseEntity = client.execute(httpPost, RESPONSE_HANDLER);

            // 요청 후 로깅
            log.debug("Response Status Code :: {}", responseEntity.getStatusCodeValue());
            log.debug("Response Header :: {}", responseEntity.getHeaders());
            log.debug("Response Body :: {}", responseEntity.getBody());

            return responseEntity;
        } catch (Exception e) {
            log.error(ERROR_LOG, "sendHttpPost()", e.getClass().getName(), e.getMessage());

            throw new BusinessException(ResponseCode.HTTP_REQUEST_ERROR);
        }
    }

    /**
     * application/json 으로 Http PUT 요청
     * @param uri        요청 uri
     * @param header     요청 헤더
     * @param body       요청 바디
     * @param timeoutSec timeout 시간
     */
    public static ResponseEntity<String> sendHttpPut(String uri, Map<String, String> header, Map<String, Object> body, Integer timeoutSec) throws BusinessException {
        // timeout 설정을 위해 RequestConfig 객체 생성
        RequestConfig requestConfig = getRequestConfig(timeoutSec);

        try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build()) {
            HttpPut httpPut = new HttpPut(uri);

            // 헤더값 추가
            header.forEach(httpPut::setHeader);
            httpPut.setHeader("Content-type", ContentType.APPLICATION_JSON.toString());

            // 바디값 추가
            Gson gson = new Gson();
            String requestBodyStr = gson.toJson(body);
            StringEntity stringEntity = new StringEntity(requestBodyStr);
            httpPut.setEntity(stringEntity);

            // 요청 전 로깅
            log.debug("Request Method :: {}", "PUT");
            log.debug("Request URI :: {}", uri);
            log.debug("Request Header :: {}", header);
            log.debug("Request Body :: {}", body.toString());

            // 요청
            ResponseEntity<String> responseEntity = client.execute(httpPut, RESPONSE_HANDLER);

            // 요청 후 로깅
            log.debug("Response Status Code :: {}", responseEntity.getStatusCodeValue());
            log.debug("Response Header :: {}", responseEntity.getHeaders());
            log.debug("Response Body :: {}", responseEntity.getBody());

            return responseEntity;
        } catch (Exception e) {
            log.error(ERROR_LOG, "sendHttpPut()", e.getClass().getName(), e.getMessage());

            throw new BusinessException(ResponseCode.HTTP_REQUEST_ERROR);
        }
    }

    /**
     * Http DELETE 요청
     * @param uri        요청 uri
     * @param header     요청 헤더
     * @param timeoutSec timeout 시간
     */
    public static ResponseEntity<String> sendHttpDelete(String uri, Map<String, String> header, Integer timeoutSec) throws BusinessException {
        // timeout 설정을 위해 RequestConfig 객체 생성
        RequestConfig requestConfig = getRequestConfig(timeoutSec);

        try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build()) {
            HttpDelete httpDelete = new HttpDelete(uri);

            // 헤더값 추가
            header.forEach(httpDelete::setHeader);

            // 요청 전 로깅
            log.debug("Request Method :: {}", "DELETE");
            log.debug("Request URI :: {}", uri);
            log.debug("Request Header :: {}", header);

            // 요청
            ResponseEntity<String> responseEntity = client.execute(httpDelete, RESPONSE_HANDLER);

            // 요청 후 로깅
            log.debug("Response Status Code :: {}", responseEntity.getStatusCodeValue());
            log.debug("Response Header :: {}", responseEntity.getHeaders());
            log.debug("Response Body :: {}", responseEntity.getBody());

            return responseEntity;
        } catch (Exception e) {
            log.error(ERROR_LOG, "sendHttpDelete()", e.getClass().getName(), e.getMessage());

            throw new BusinessException(ResponseCode.HTTP_REQUEST_ERROR);
        }
    }

    /**
     * Map 형태의 파라미터 들을 http 요청을 위해 NameValuePair 로 만드는 메서드
     * @param params NameValuePair 리스트로 변환하기 위한 Map 객체. String, String Map 객체만 가능
     */
    private static List<NameValuePair> convertParam(Map<String, String> params) {
        List<NameValuePair> nameValuePairs = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return nameValuePairs;
    }

    /**
     * 파라미터로 받은 timeout 설정 값으로 RequestConfig 객체 반환
     * null 혹은 에러 발생시 default 객체 반환
     * @param timeoutSec timeout 설정 값 (초)
     */
    private static RequestConfig getRequestConfig(Integer timeoutSec) {
        try {
            if (timeoutSec != null) {
                return RequestConfig.custom()
                        .setConnectTimeout(timeoutSec * 1000)
                        .setConnectionRequestTimeout(timeoutSec * 1000)
                        .setSocketTimeout(timeoutSec * 1000)
                        .build();
            }
        } catch (Exception e) {
            log.error(ERROR_LOG, "getRequestConfig()", e.getClass().getName(), e.getMessage());
        }

        return RequestConfig.DEFAULT;
    }
}
