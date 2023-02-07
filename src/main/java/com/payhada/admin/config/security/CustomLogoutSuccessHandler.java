package com.payhada.admin.config.security;

import com.payhada.admin.code.ResponseCode;
import com.payhada.admin.common.setting.CommonResponse;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.service.user.LoginService;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.payhada.admin.common.util.MessageSourceUtils.getMessage;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final LoginService loginService;

    public CustomLogoutSuccessHandler(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ResponseCode responseCode = ResponseCode.API_STATUS_OK;

        response.setStatus(responseCode.getStatus());

        // Logout Handler 에서는 session 정보가 이미 지워졌으므로 다국어처리를 직접 해줌
        LoginDTO logoutDTO = (LoginDTO) authentication.getPrincipal();
        LoginDTO loginDTO = loginService.getLoginDTO(logoutDTO);

        String languageCd = loginDTO.getLanguageCd();
        Locale locale = new Locale(languageCd);

        CommonResponse commonResponse = CommonResponse.builder()
                .resultCode(responseCode.getCode())
                .resultMsg(getMessage(responseCode.getCode(), locale))
                .build();

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        if (jsonConverter.canWrite(commonResponse.getClass(), jsonMimeType)) {
            jsonConverter.write(commonResponse, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }
}
