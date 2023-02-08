package com.payhada.admin.config.security;

import com.payhada.admin.config.filter.ExceptionHandlerFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	private static final String[] PERMIT_ALL = {
			"/api/v2/locale", "/api/v2/loginCheck", "/api/v2/test", "/api/v2/test3"
	};

	private final JsonUsernamePasswordAuthenticationFilter authenticationFilter;
	private final CustomLogoutSuccessHandler logoutSuccessHandler;
	private final CustomAuthenticationEntryPoint authenticationEntryPoint;
	private final CustomAccessDeniedHandler accessDeniedHandler;
	private final ExceptionHandlerFilter exceptionHandlerFilter;

	public SecurityConfig(JsonUsernamePasswordAuthenticationFilter authenticationFilter,
						  CustomLogoutSuccessHandler logoutSuccessHandler, CustomAuthenticationEntryPoint authenticationEntryPoint,
						  CustomAccessDeniedHandler accessDeniedHandler, ExceptionHandlerFilter exceptionHandlerFilter) {
		this.authenticationFilter = authenticationFilter;
		this.logoutSuccessHandler = logoutSuccessHandler;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.accessDeniedHandler = accessDeniedHandler;
		this.exceptionHandlerFilter = exceptionHandlerFilter;
	}


	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/static/**", "/login", "/");
	}

	/**
	 * @apiNote
	 * {@code .anyRequest().authenticated()} 사용 시 ID/PW 를 통한 1차 인증만 하여도
	 * 접근이 가능하기 때문에 2차 인증 후 최소 한개 이상의 권한이 있는 사람만 접근가능 하도록
	 * {@code .anyRequest().access("@loginService.isFullAuthenticated(principal)")} 를 사용함
	 * 해당 메서드를 통해 인증&인가 확인
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
	  	http
		  	.authorizeRequests()
		  		.antMatchers(PERMIT_ALL).permitAll()
				.antMatchers("/api/v2/test2").hasAnyAuthority("0000", "4100000102")
				.anyRequest().access("@loginService.isFullAuthenticated(principal)");
//				.anyRequest().authenticated();
		http.csrf().disable();
		http
			.formLogin().disable()
			.logout()
				.logoutUrl("/api/v2/logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true)
		.and()
			.addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.accessDeniedHandler(accessDeniedHandler)
		;
	}

}
