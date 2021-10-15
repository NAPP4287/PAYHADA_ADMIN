package com.payhada.admin.connector;

import java.util.Map;

public interface Connector {

	void setConnectionUrl(String url);
	String sendHttpGet(Map<String, String> header, Map<String, String> body);
	String sendHttpPost(Map<String, Object> header, Map<String, Object> body);
}
