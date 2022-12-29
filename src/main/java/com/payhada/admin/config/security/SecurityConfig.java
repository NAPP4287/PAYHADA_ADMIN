package com.payhada.admin.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payhada.admin.service.user.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
/*@AllArgsConstructor*/
public class SecurityConfig extends WebSecurityConfigurerAdapter  {

	@Autowired
	private LoginService loginService;

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/static/**", "/login", "/");
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	  	http
		  	.authorizeRequests()
		  	.antMatchers("/api/v2/login").permitAll()
		  	.anyRequest().authenticated();
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
				new CustomAuthenticationSuccessHandler(),
				new CustomAuthenticationFailureHandler()
		);
	}

}
