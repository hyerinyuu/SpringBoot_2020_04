package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.biz.sec.service.SecurityUserServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
// @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final SecurityUserServiceImpl sUserService;
	private final UtilsConfig uConfig;
	
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
	 * security가 어떻게 작동할지 설정하는 method
	 * passwordEncoder에 bean을 주입
	 * uService로부터 넘어오는 값을 받아 matches method로 체크한다.
	 * (decode까지 해서 사용자 로그인 정보를 체크함)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(auth);
		auth.userDetailsService(sUserService).passwordEncoder(sUserService.getPasswordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(web);
		
		/*
		 * security에서 검사하지 않을 요청(무시해라)
		 */
		web.ignoring().antMatchers("/statis/css/**", "/static/js/**").antMatchers("/static/images/**").antMatchers("/static/music/**");
	}
	
	
	/*
	 * html templates 파일에서 sec: tag를 사용할 때
	 * 값, 설정, 함수 등을 사용할 수 있도록 객체를 전달하는 용도
	 * 
	 * thymeleaf-extras-springsecurity5에서는
	 * Security 설정된 config에서 자동 주입이 된다.
	 * 특히 config 클래스를 WebSecurity configurer
	 */
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		
		return new SpringSecurityDialect();
	}

	
}
