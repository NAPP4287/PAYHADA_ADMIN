package com.payhada.admin.config.security;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.model.LoginDTO;
import com.payhada.admin.model.ResponseDTO;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final LoginService loginService;

    private final RequestCache requestCache = new HttpSessionRequestCache();

    public CustomAuthenticationSuccessHandler(LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        LoginDTO loginDTO = (LoginDTO) authentication.getPrincipal();

        // 로그인 성공 시 비밀번호 실패 카운트, 잠금 시간 초기화
        loginService.resetLoginFailureData(loginDTO.getUserNo());

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if(savedRequest != null) {
            requestCache.removeRequest(request, response);
            clearAuthenticationAttributes(request);
        }

        LoginDTO userInfo = (LoginDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userInfo.setPwd(null);
        log.debug("userInfo :: {}", userInfo);

        ResponseDTO responseDTO = makeResponse(loginDTO);

        // application/json
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;

        if (jsonConverter.canWrite(responseDTO.getClass(), jsonMimeType)) {
            jsonConverter.write(responseDTO, jsonMimeType, new ServletServerHttpResponse(response));
        }
    }

    private ResponseDTO makeResponse(LoginDTO loginDTO) {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("userNo", loginDTO.getUserNo());
        responseData.put("loginId", loginDTO.getId());
        responseData.put("roleGroupList", loginDTO.getEmployeeRoleMappDTOList().stream()
                .map(dto -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("roleGroupCode", dto.getRoleGroupCode());
                    map.put("roleGroupName", dto.getRoleGroupName());
                    return map;
                }).collect(Collectors.toList()));

        return ResponseDTO.builder()
                .resultCode(200)
                .resultMsg("정상")
                .data(new ObjectMapper().convertValue(responseData, new TypeReference<Map<String, Object>>() {}))
                .build();
    }
}
