package com.payhada.admin.common.setting;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/properties/ncp-${spring.profiles.active}.properties")
@Getter
public class NcpPropertiesDTO {
    @Value("${access-key}")
    private String accessKey;

    @Value("${secret-key}")
    private String secretKey;

    @Value("${app-service.cloud-outbound-mailer.url}")
    private String mailServiceUrl;
}
