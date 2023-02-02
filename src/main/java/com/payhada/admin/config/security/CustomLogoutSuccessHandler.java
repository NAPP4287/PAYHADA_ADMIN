package com.payhada.admin.config.security;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.CommonResponse;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ResponseCode responseCode = ResponseCode.SUCCESSFUL_LOGOUT;

        response.setStatus(responseCode.getStatus());

        CommonResponse commonResponse = CommonResponse.create(responseCode.getCode());

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        if (jsonConverter.canWrite(commonResponse.getClass(), jsonMimeType)) {
            jsonConverter.write(commonResponse, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }
}
