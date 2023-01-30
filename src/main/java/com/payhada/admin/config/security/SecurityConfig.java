package com.payhada.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.service.MailService;
import com.payhada.admin.service.user.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	private static final String[] PERMIT_ALL = {
			"/api/v2/login", "/api/v2/test", "/api/v2/test3"
	};

	private final LoginService loginService;

	private final MailService mailService;

	public SecurityConfig(LoginService loginService, MailService mailService) {
		this.loginService = loginService;
		this.mailService = mailService;
	}


	@Override
	public void configure(WebSecurity web) throws Exception
	{
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
			.addFilterBefore(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
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
