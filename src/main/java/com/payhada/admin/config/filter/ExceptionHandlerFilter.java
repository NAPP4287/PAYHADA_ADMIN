package com.payhada.admin.config.filter;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ControllerAdvice 에서 핸들링 할 수 없는 Filter 에서 발생한 Exception 을 핸들링 하기 위한 필터 클래스
 */
@Slf4j
@Component
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error("[{}] :: {} - {}", "doFilterInternal()", e.getClass().getName(), e.getMessage());

            ResponseCode responseCode = ResponseCode.API_SERVER_ERROR;

            response.setStatus(responseCode.getStatus());

            CommonResponse commonResponse = CommonResponse.create(responseCode.getCode());

            MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
            MediaType jsonMimeType = MediaType.APPLICATION_JSON;

            if (jsonConverter.canWrite(commonResponse.getClass(), jsonMimeType)) {
                jsonConverter.write(commonResponse, jsonMimeType, new ServletServerHttpResponse(response));
            }
        }
    }
}
