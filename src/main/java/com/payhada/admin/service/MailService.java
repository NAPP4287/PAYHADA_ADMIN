package com.payhada.admin.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.connector.Connector;
import com.payhada.admin.common.setting.NcpPropertiesDTO;
import com.payhada.admin.common.util.NcpUtils;
import com.payhada.admin.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class MailService {

    private static final String ADMIN_AUTH_SID = "4333";

    private final NcpPropertiesDTO ncpPropertiesDTO;
    private final Connector httpConnector;
    private final ObjectMapper objectMapper;

    public MailService(NcpPropertiesDTO ncpPropertiesDTO, Connector httpConnector, ObjectMapper objectMapper) {
        this.ncpPropertiesDTO = ncpPropertiesDTO;
        this.httpConnector = httpConnector;
        this.objectMapper = objectMapper;
    }

    public Map<String, Object> send(Map<String, Object> body) throws BusinessException {
        String method = "POST";
        String endpoint = "/api/v1/mails";
        String url = ncpPropertiesDTO.getMailServiceUrl() + endpoint;

        log.debug("Send NCP Mail Service");
        log.debug("[{}] {}", method, url);
        log.debug("Request Body :: {}", body);

        try {
            Map<String, String> header = NcpUtils.generateHeader(method, endpoint);

            log.debug("Request NCP - createMailRequest API");
            ResponseEntity<String> responseEntity = httpConnector.postJson(url, header, body);
            HttpStatus httpStatus = responseEntity.getStatusCode();
            String responseStr = responseEntity.getBody();
            log.debug("Response NCP - createMailRequest API :: {}", responseStr);

            Map<String, Object> resultMap = objectMapper.readValue(responseStr, new TypeReference<Map<String, Object>>() {});
            resultMap.put("httpStatusCode", httpStatus.value());

            return resultMap;
        } catch (Exception e) {
            log.error(e.getMessage());

            throw new BusinessException(ResponseCode.NCP_FAIL_MAIL_SERVICE);
        }
    }

    public Map<String, Object> sendAdminAuthMail(String email, String otpCode, String authDate) throws BusinessException {
        Map<String, String> recipient = new HashMap<>();
        recipient.put("address", email);
        recipient.put("type", "R");
        List<Map<String, String>> recipients = Collections.singletonList(recipient);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("otpCode", otpCode);
        parameters.put("authDate", authDate);

        Map<String, Object> mailBody = new HashMap<>();
        mailBody.put("templateSid", ADMIN_AUTH_SID);
        mailBody.put("parameters", parameters);
        mailBody.put("recipients", recipients);

        try {
            return send(mailBody);
        } catch (Exception e) {
            log.error("MailService - sendAdminAuthMail Error");
            log.error(e.getMessage());

            throw new BusinessException(ResponseCode.NCP_FAIL_MAIL_SERVICE);
        }
    }
}
