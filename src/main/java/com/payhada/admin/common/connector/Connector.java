package com.payhada.admin.common.connector;

import com.payhada.admin.exception.BusinessException;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface Connector {

	void setConnectionUrl(String url);
	String sendHttpGet(Map<String, String> header, Map<String, String> body);
	String sendHttpPost(Map<String, Object> header, Map<String, Object> body);

	ResponseEntity<String> postJson(String url, Map<String, String> header, Map<String, Object> body) throws BusinessException;
}
