package com.payhada.admin.config.security;

import com.payhada.admin.code.ErrorCode;
import com.payhada.admin.common.setting.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.OK.value());

        Response responseDTO = Response.builder()
                .resultCode(403)
                .error(Response.Error.builder()
                        .code(ErrorCode.USER_AUTH_REQUIRED.getCode())
                        .message(ErrorCode.USER_AUTH_REQUIRED.getMessage())
                        .build())
                .build();

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        if (jsonConverter.canWrite(responseDTO.getClass(), jsonMimeType)) {
            jsonConverter.write(responseDTO, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }
}
