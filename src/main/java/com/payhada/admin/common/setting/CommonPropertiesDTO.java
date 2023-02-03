package com.payhada.admin.common.setting;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:/properties/common-${spring.profiles.active}.properties")
@Getter
@ToString
public class CommonPropertiesDTO {
	@Value("${payhada.server.url}")
	private String payhadaServerUrl;
	
	@Value("${payhada.server.admin.port}")
	private String payhadaServerAdminPort;
	
	@Value("${payhada.server.core.port}")
	private String payhadaServerCorePort;
	
	@Value("${payhada.server.app.port}")
	private String payhadaServerAppPort;
	
	@Value("${payhada.server.fep.port}")
	private String payhadaServerFepPort;
	
	@Value("${payhada.server.batch.port}")
	private String payhadaServerBatchPort;
	
	@Value("${payhada.server.aml.port}")
	private String payhadaServerAmlPort;
}
