package com.payhada.admin.common.setting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:/properties/common-${spring.profiles.active}.properties")
public class CommonPropertiesDTO {
	@Value("${payhada.server.url}")
	private String payhada_server_url;
	
	@Value("${payhada.server.admin.port}")
	private String payhada_server_admin_port;
	
	@Value("${payhada.server.core.port}")
	private String payhada_server_core_port;
	
	@Value("${payhada.server.app.port}")
	private String payhada_server_app_port;
	
	@Value("${payhada.server.fep.port}")
	private String payhada_server_fep_port;
	
	@Value("${payhada.server.batch.port}")
	private String payhada_server_batch_port;
	
	@Value("${payhada.server.aml.port}")
	private String payhada_server_aml_port;

	public String getPayhada_server_url() {
		return payhada_server_url;
	}

	public void setPayhada_server_url(String payhada_server_url) {
		this.payhada_server_url = payhada_server_url;
	}

	public String getPayhada_server_admin_port() {
		return payhada_server_admin_port;
	}

	public void setPayhada_server_admin_port(String payhada_server_admin_port) {
		this.payhada_server_admin_port = payhada_server_admin_port;
	}

	public String getPayhada_server_core_port() {
		return payhada_server_core_port;
	}

	public void setPayhada_server_core_port(String payhada_server_core_port) {
		this.payhada_server_core_port = payhada_server_core_port;
	}

	public String getPayhada_server_app_port() {
		return payhada_server_app_port;
	}

	public void setPayhada_server_app_port(String payhada_server_app_port) {
		this.payhada_server_app_port = payhada_server_app_port;
	}

	public String getPayhada_server_fep_port() {
		return payhada_server_fep_port;
	}

	public void setPayhada_server_fep_port(String payhada_server_fep_port) {
		this.payhada_server_fep_port = payhada_server_fep_port;
	}

	public String getPayhada_server_batch_port() {
		return payhada_server_batch_port;
	}

	public void setPayhada_server_batch_port(String payhada_server_batch_port) {
		this.payhada_server_batch_port = payhada_server_batch_port;
	}

	public String getPayhada_server_aml_port() {
		return payhada_server_aml_port;
	}

	public void setPayhada_server_aml_port(String payhada_server_aml_port) {
		this.payhada_server_aml_port = payhada_server_aml_port;
	}

	@Override
	public String toString() {
		return "CommonPropertiesDTO [payhada_server_url=" + payhada_server_url + ", payhada_server_admin_port=" + payhada_server_admin_port + ", payhada_server_core_port=" + payhada_server_core_port + ", payhada_server_app_port=" + payhada_server_app_port + ", payhada_server_fep_port=" + payhada_server_fep_port + ", payhada_server_batch_port=" + payhada_server_batch_port + ", payhada_server_aml_port=" + payhada_server_aml_port + "]";
	}


	
}
