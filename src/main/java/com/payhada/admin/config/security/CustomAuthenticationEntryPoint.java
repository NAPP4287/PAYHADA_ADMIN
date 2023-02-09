package com.payhada.admin.config.security;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.CommonResponse;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Spring Server 에 요청 시 미인증 상태인 경우 핸들링 하여 응답 포맷 지정
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ResponseCode responseCode = ResponseCode.USER_UNAUTHORIZED;

        response.setStatus(responseCode.getStatus());

        CommonResponse commonResponse = CommonResponse.create(responseCode.getCode());

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        if (jsonConverter.canWrite(commonResponse.getClass(), jsonMimeType)) {
            jsonConverter.write(commonResponse, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }
}
