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
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.payhada.admin.common.util.MessageSourceUtils.getMessage;

@Slf4j
@Component
public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

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

        // POST가 아니거나 JSON이 아닌 경우
        if (!request.getMethod().equals(HTTP_METHOD) || !request.getContentType().equals("application/json")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        LoginDTO loginDto = objectMapper.readValue(StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8), LoginDTO.class);

        String username = loginDto.getId();
        String password = loginDto.getPwd();
        String secret = loginDto.getSecret();

        if ((StringUtils.isEmpty(username) && StringUtils.isEmpty(password)) && StringUtils.isEmpty(secret)) {
            throw new AuthenticationServiceException("DATA IS MISS");
        }

        // 2차 인증 일 경우 session 에서 1차 로그인 때 저장된 객체를 찾아, 2차 인증시 사용자가 입력한 OTP CODE (secret) 을 세팅해줌
        if (!StringUtils.isEmpty(secret)) {
            try {
                LoginDTO sessionDto = (LoginDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                sessionDto.setSecret(secret);
                loginDto = sessionDto;
            } catch (Exception e) {
                 throw new InsufficientAuthenticationException(getMessage("unauthenticated-1", request.getSession()));
            }
        }

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(loginDto, password);

        // Allow subclasses to set the "details" property
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);
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
