/**
 * JSON Format API 인증 처리 커스텀
 * admin_v2
 */
package com.payhada.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.config.CachedBodyHttpServletRequest;
import com.payhada.admin.model.LoginDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper;

    public static final String SPRING_SECURITY_FROM_USERNAME_KEY = "id";
    public static final String SPRING_SECURITY_FROM_PASSWORD_KEY = "pwd";
    public static final String HTTP_METHOD = "POST";
    public static final String API_LOGIN_URL_PATH = "/api/v2/login";

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher(API_LOGIN_URL_PATH, HTTP_METHOD);

    @Autowired
    public JsonUsernamePasswordAuthenticationFilter(ObjectMapper objectMapper,
                                                    AuthenticationManager authenticationManager,
                                                    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler,
                                                    CustomAuthenticationFailureHandler customAuthenticationFailureHandler
                                                    ) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER);
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(customAuthenticationSuccessHandler);
        setAuthenticationFailureHandler(customAuthenticationFailureHandler);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException,
            IOException, ServletException {
        log.debug("JsonUsernamePasswordAuthenticationFilter is running...");

        if (!request.getMethod().equals(HTTP_METHOD) || !request.getContentType().equals("application/json")) {//POST가 아니거나 JSON이 아닌 경우
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        LoginDTO loginDto = objectMapper.readValue(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8), LoginDTO.class);

        String username = loginDto.getId();
        String password = loginDto.getPwd();

        if (username == null || password == null) {
            throw new AuthenticationServiceException("DATA IS MISS");
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest); //getAuthenticationManager를 커스텀해줌
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;

        // 로그인 요청일 경우 failure handler 에서 로그인 정보를 얻기 위해 request 객체 처리
        // request 의 getInputStream() 사용시 inputStream 은 일회용 이기 때문에 복사하여 사용
        if (httpServletRequest.getRequestURI().startsWith(API_LOGIN_URL_PATH)) {
            HttpServletRequest wrapper = new CachedBodyHttpServletRequest((HttpServletRequest) req);
            super.doFilter(wrapper, res, chain);
        } else {
            super.doFilter(req, res, chain);
        }
    }
}
