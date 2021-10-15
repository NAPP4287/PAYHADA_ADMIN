// package com.payhada.admin.config.convert;

// import org.apache.logging.log4j.core.config.Order;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.BeanIds;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.web.authentication.AuthenticationFailureHandler;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// import com.payhada.admin.config.UserAuthenticationProvider;
// import com.payhada.admin.config.handler.CustomAuthenticationSuccessHandler;
// import com.payhada.admin.config.handler.CustomAuthenticationFailureHandler;

// @Configuration
// @EnableWebSecurity 
// @Order(3)
// /*@AllArgsConstructor*/
// public class SecurityConfig extends WebSecurityConfigurerAdapter  {
//     @Autowired
//     private UserAuthenticationProvider authenticationProvider;

// 	@Autowired
// 	private AuthenticationFailureHandler failureHandler;
// 	@Autowired
// 	private AuthenticationSuccessHandler successHandler;
		
// 	@Autowired
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
// 		auth.authenticationProvider(authenticationProvider);   // 로그인 정보를 확인하고, 권한을 확인 => 인증 토근 발행
//     }

//     @Value("${spring.profiles.active}")
// 	private String active;

//     @Override
//     public void configure(WebSecurity web) throws Exception
//     {
//         // static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
//         web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/images/**");
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {    	  
// 		  http.authorizeRequests()
// 		  	.antMatchers("/users/userDetail/searchAddr/**").permitAll()
// 		  	.antMatchers("/member/insertMember/searchAddr/**").permitAll()
// 		    .antMatchers("/member/permit", "/agent/**", "/setting/rates").permitAll()
// 		  	.antMatchers("/config/**").hasAnyRole("A", "D", "M")
// 		  	.anyRequest().authenticated()
// 		    .and().addFilterBefore(customUsernamePasswordAuthenticationFilter(), CustomUsernamePasswordAuthenticationFilter.class);
// 		  http.csrf().ignoringAntMatchers("/**");
// 		  http.formLogin()
// 		  	.loginPage("/login")   
// 			.usernameParameter("id")
// 			.passwordParameter("pwd")
// 		  	.loginProcessingUrl("/login")
// 		  	// .loginProcessingUrl("/login/loginCheck")
// 			// .successHandler(successHandler)
// 			// .failureHandler(failureHandler)
// 			// .defaultSuccessUrl("/main")
// 		  	// .failureUrl("/login")
// 		  	.permitAll();
// 		  http.logout()
// 		  	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
// 		  	.logoutSuccessUrl("/login") 
// 		  	.invalidateHttpSession(true);
// 		  http.exceptionHandling()
// 		  	.accessDeniedPage("/login/denied");

// //		if(active.equals("test") || active.equals("prod")) {
// //			http.requiresChannel().antMatchers("/login").requiresSecure();
// //		}
//     }


// 	// // 로그인 성공 처리를 위한 Handler
// 	// @Bean
// 	// public AuthenticationSuccessHandler successHandler() {
// 	// 	// log.info("[ BEAN ] : AuthenticationSuccessHandler");
// 	// 	// loginIdname, defaultUrl
// 	// 	return new CustomAuthenticationSuccessHandler("username", "/loginSuccess");
// 	// }

// 	// // 실패 처리를 위한 Handler
// 	// @Bean
// 	// public AuthenticationFailureHandler failureHandler() {
// 	// //  log.info("[ BEAN ] : failureHandler");
// 	// 	return new CustomAuthenticationFailureHandler("username", "password" , "loginRedirectUrl" , "exceptionMsgName" , "/login". "dsf");
// 	// }
	
// 	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
// 	@Override
// 	public AuthenticationManager authenticationManagerBean() throws Exception {
// 		// TODO Auto-generated method stub
// 		return super.authenticationManagerBean();
// 	}

// 	@Bean
// 	public CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
// 		CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter();
// 		customUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
// 		return customUsernamePasswordAuthenticationFilter;
// 	}
// 	// @Autowired
// 	// private CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter;

// }
