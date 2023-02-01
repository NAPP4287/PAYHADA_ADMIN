package com.payhada.admin.common.connector;


import com.google.gson.Gson;
import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HttpConnector implements Connector {

	private String url;

	public void setConnectionUrl(String url) {
		this.url=url;
	}
	
	public String sendHttpGet(Map<String, String> header,Map<String, String> body) {
		String returnData="";
		try {
			HttpClient client = HttpClientBuilder.create().build();
			StringBuffer url =new StringBuffer();
			if(this.url==null||this.url.equals("")) {
				log.info("URL NOT FOUND EXCEPTION");
				throw new Exception();
			}
			url.append(this.url);
			
	        Iterator<String> bodyIter = body.keySet().iterator();
	        int count=0;
	        
	        //바디부분 추가(데이터)
	        while( bodyIter.hasNext() ){
	            String key = bodyIter.next();
	            if(count==0) {
	            	url.append("?");
	            }else {
	            	url.append("&");
	            }
	           String formatter =  body.get(key).replaceAll(" ","%20");
	            url.append(key+"="+formatter); 
	            count++;
	        }
			HttpGet httpGet = new HttpGet(url.toString());
			
			//헤더값 추가
	        Iterator<String> headerIter = header.keySet().iterator();
	        while( headerIter.hasNext() ){
	            String key = headerIter.next(); 
	            httpGet.addHeader(key,header.get(key));
	            log.info("data : "+key+","+header.get(key));
	        }
			
			log.info("[DATA TRANSFER GET] URL ="+url);
	        
			
			HttpResponse response = client.execute(httpGet);

			returnData = EntityUtils.toString(response.getEntity(), "utf-8"); 
			
			log.info("###########return data"+returnData);
		} catch (IOException ioeE) {
			log.info("입출력 오류");
			log.info(ioeE.getMessage());
		}catch (Exception e) {
			log.info("HTTP 전송오류 ");
			log.info(e.getMessage());
		}
		
		return returnData;
	}
	
	public String sendHttpPost(Map<String, Object> header, Map<String, Object> body) {
		String returnData = null;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			StringBuffer url = new StringBuffer();
			if (this.url == null || this.url.equals("")) {
				log.info("URL NOT FOUND EXCEPTION");
				throw new Exception();
			}
			url.append(this.url);
			
			
//			Gson gson = new GsonBuilder().setPrettyPrinting().create();
//			String json =gson.toJson(body);
			
			
			/*			Iterator<String> bodyIter = body.keySet().iterator();
						int count = 0;
						log.debug("body :" + body);
						ObjectMapper mapper = new ObjectMapper();
						String jsonStr = "";
						jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body);*/

			HttpPost httpPost = new HttpPost(url.toString());
//			StringEntity params = new StringEntity(json, "UTF-8");
//			params.setContentType("application/json; charset=UTF-8");
//			httpPost.setEntity(params);

			List<NameValuePair> testParam = new ArrayList<>();
			Iterator<String> keys = body.keySet().iterator();
			while( keys.hasNext() ){
				String key = keys.next();
				testParam.add(new BasicNameValuePair(key, (String) body.get(key)));
				log.info("["+key+"]"+body.get(key));
			}
			httpPost.setEntity(new UrlEncodedFormEntity(testParam, "UTF-8"));

			
			httpPost.setHeader("Accept", "application/json");
			// 헤더값 추가
			Iterator<String> headerIter = header.keySet().iterator();
			while (headerIter.hasNext()) {
				String key = headerIter.next();
				httpPost.addHeader(key, (String)header.get(key));
			}
			log.info("[DATA TRANSFER POST] URL = " + url);
//			log.info("[DATA TRANSFER POST] DATA = \n" + json);
			log.info("[DATA TRANSFER POST] DATA = \n" + testParam);

			HttpResponse response = client.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				returnData = handler.handleResponse(response);
				log.info("데이터 = " + returnData);
			} else {
				log.info("response is error : " + response.getStatusLine().getStatusCode());
			}
		} catch (IOException ioeE) {
			log.info("입출력 오류");
			log.info(ioeE.getMessage());
		} catch (Exception e) {
			log.info("HTTP 전송오류 ");
			log.info(e.getMessage());
		}

		return returnData;
	}

	@Override
	public String postJson(String url, Map<String, String> header, Map<String, Object> body) throws BusinessException {
		log.debug("HTTP POST REQUEST");
		log.debug("url :: {}", url);
		log.debug("header :: {}", header);
		log.debug("body :: {}", body);

		try {
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/json");
			header.forEach(httpPost::setHeader);

			Gson gson = new Gson();
			String bodyStr = gson.toJson(body);
			HttpEntity httpEntity = new StringEntity(bodyStr);
			httpPost.setEntity(httpEntity);

			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			CloseableHttpResponse response = httpClient.execute(httpPost);

			String responseStr;
			int responseStatusCode = response.getStatusLine().getStatusCode();
			if (responseStatusCode == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				responseStr = handler.handleResponse(response);
			} else {
				HttpEntity entity = response.getEntity();
				responseStr = EntityUtils.toString(entity, StandardCharsets.UTF_8);
			}

			log.debug("Response Status :: {}", responseStatusCode);
			log.debug("Response Body :: {}", responseStr);

			return responseStr;
		} catch (Exception e) {
 			log.error("HTTP POST REQUEST 실패");
			log.error(e.getMessage());

			throw new BusinessException(ResponseCode.API_SERVER_ERROR);
		}
	}
}
