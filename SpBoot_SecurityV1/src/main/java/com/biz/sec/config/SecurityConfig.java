package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// super.configure(http);
		
		// 순서 중요함
		// /**는 항상 제일 마지막에 있어야함
		httpSecurity.authorizeRequests()
		.antMatchers("/hello").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user/login").permitAll()
		.antMatchers("/**").permitAll();
		
		httpSecurity.authorizeRequests()
		.and().formLogin()
		.loginPage("/user/login")
		.failureUrl("/user/login?error")
		.usernameParameter("username")
		.passwordParameter("password")
		.and()
		.logout()
		.logoutSuccessUrl("/");
		
		
	}
	
	/*
	 * spring bena configuration에 작성하던 passwordEncoder를 설정
	 * <bean></bean>을 대신하는 java 설정
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	
}
