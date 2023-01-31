package com.payhada.admin.common.util;

import com.payhada.admin.code.ErrorCode;
import com.payhada.admin.common.setting.NcpPropertiesDTO;
import com.payhada.admin.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class NcpUtils {

    public static NcpPropertiesDTO ncpPropertiesDTOStatic;

    private final NcpPropertiesDTO ncpPropertiesDTO;

    public NcpUtils(NcpPropertiesDTO ncpPropertiesDTO) {
        this.ncpPropertiesDTO = ncpPropertiesDTO;
    }

    @PostConstruct
    private void init() {
        NcpUtils.ncpPropertiesDTOStatic = ncpPropertiesDTO;
    }

    public static String makeSignature(String method, String url, String timestamp) throws BusinessException {
        log.debug("NCP MAKE SIGNATURE");
        log.debug("method :: {} / url :: {}", method, url);

        try {
            String message = new StringBuilder()
                    .append(method)
                    .append(" ")
                    .append(url)
                    .append("\n")
                    .append(timestamp)
                    .append("\n")
                    .append(ncpPropertiesDTOStatic.getAccessKey())
                    .toString();

            log.debug("message :: {}", message);

            SecretKeySpec signingKey = new SecretKeySpec(ncpPropertiesDTOStatic.getSecretKey().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));

            return Base64.encodeBase64String(rawHmac);
        } catch (Exception e) {
            log.error("NcpUtils - makeSignature Error");
            log.error(e.getMessage());

            throw new BusinessException(ErrorCode.NCP_MAKE_SIGNATURE_ERR);
        }
    }

    public static Map<String, String> generateHeader(String method, String url) throws BusinessException {
        try {
            String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
            String signature = makeSignature(method, url, timestamp);

            Map<String, String> header = new HashMap<>();
            header.put("x-ncp-apigw-timestamp", timestamp);
            header.put("x-ncp-iam-access-key", ncpPropertiesDTOStatic.getAccessKey());
            header.put("x-ncp-apigw-signature-v2", signature);

            return header;
        } catch (Exception e) {
            log.error("NcpUtils - generateHeader Error");
            log.error(e.getMessage());

            throw new BusinessException(ErrorCode.NCP_GENERATE_HEADER_ERR);
        }
    }
}
