package com.payhada.admin.connector;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpConnector implements Connector{
	Logger log = LoggerFactory.getLogger(HttpConnector.class);
	
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
}
