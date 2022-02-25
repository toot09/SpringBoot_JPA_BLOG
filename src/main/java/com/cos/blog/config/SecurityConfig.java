package com.cos.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration	// 빈등록 (IoC관리)
@EnableWebSecurity	//	필터에 시큐리티가 등록된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) 	// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests()				// Request가 들어왔을때
				.antMatchers("/auth/**")					// 이 url (여기서는 auth로 시작하는 url) 로 들어왔을때 
				.permitAll()										// 모두 허용해준다.
				.anyRequest()									// 위가 아닌 모든 Request는 
				.authenticated()								// 인증해야함.
			.and()												// 추가로 
				.formLogin()										// 로그인 페이지 세팅할
				.loginPage("/auth/loginForm");		// 경로는 여기임 (로그인이 필요할때는 여기 로그인 페이지로 자동간다)
		
		
	}
}
