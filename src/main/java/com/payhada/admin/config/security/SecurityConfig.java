package com.payhada.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.code.ErrorCode;
import com.payhada.admin.common.setting.Response;
import com.payhada.admin.service.MailService;
import com.payhada.admin.service.user.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	private static final String[] PERMIT_ALL = {
			"/api/v2/test", "/api/v2/test3"
	};

	private final LoginService loginService;
	private final MailService mailService;
	private final CustomLogoutSuccessHandler logoutSuccessHandler;
	private final CustomAuthenticationEntryPoint authenticationEntryPoint;
	private final CustomAccessDeniedHandler accessDeniedHandler;

	public SecurityConfig(LoginService loginService, MailService mailService, CustomLogoutSuccessHandler logoutSuccessHandler,
						  CustomAuthenticationEntryPoint authenticationEntryPoint, CustomAccessDeniedHandler accessDeniedHandler) {
		this.loginService = loginService;
		this.mailService = mailService;
		this.logoutSuccessHandler = logoutSuccessHandler;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.accessDeniedHandler = accessDeniedHandler;
	}


	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/static/**", "/login", "/");
	}

	/**
	 * @apiNote
	 * {@code .anyRequest().authenticated()} 사용 시 ID/PW 를 통한 1차 인증만 하여도
	 * 접근이 가능하기 때문에 2차 인증 후 최소 한개 이상의 권한이 있는 사람만 접근가능 하도록
	 * {@code .anyRequest().hasAnyAuthority(loginService.getAllRoleGroupNames())} 를 사용함.
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	  	http
		  	.authorizeRequests()
		  		.antMatchers(PERMIT_ALL).permitAll()
				.antMatchers("/api/v2/test2").hasAnyAuthority("0000", "4100000102")
				.anyRequest().hasAnyAuthority(loginService.getAllRoleGroupNames());
//				.anyRequest().authenticated();
		http.csrf().disable();
		http
			.formLogin().disable()
			.logout()
				.logoutUrl("/api/v2/logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true)
			.and()
			.addFilterBefore(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.accessDeniedHandler(accessDeniedHandler)
		;
	}

	@Bean
	public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() {
		return new JsonUsernamePasswordAuthenticationFilter(
				new ObjectMapper(),
				new JsonAuthenticationManager(loginService),
				new CustomAuthenticationSuccessHandler(loginService, mailService),
				new CustomAuthenticationFailureHandler(loginService)
		);
	}
}
