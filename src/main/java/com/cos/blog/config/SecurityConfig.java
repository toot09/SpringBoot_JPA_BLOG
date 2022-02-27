package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;


@Configuration	// 빈등록 (IoC관리)
@EnableWebSecurity	//	필터에 시큐리티가 등록된다.
@EnableGlobalMethodSecurity(prePostEnabled = true) 	// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean	// IoC가 됨.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	// 시큐리티가 대신 로그인할때 password를 가로채기 하는데
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());		
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() 	// scrf토큰 비활성화 (테스트 시 걸어두는게 좋음)
			.authorizeHttpRequests()														// Request가 들어왔을때
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")		// 이 url 로 들어왔을때 
				.permitAll()																				// 모두 허용해준다.
				.anyRequest()																			// 위가 아닌 모든 Request는 
				.authenticated()																		// 인증해야함.
			.and()																						// 추가로 
				.formLogin()																				// 로그인 페이지 세팅할
				.loginPage("/auth/loginForm")													// 경로는 여기임 (로그인이 필요할때는 여기 로그인 페이지로 자동간다)
				.loginProcessingUrl("/auth/loginProc")										// 로그인 요청이 이 URI로 들어오게 되면 처리해준다 (내가 로그인 처리를 만들필요가 없음)
				.defaultSuccessUrl("/");															// 로그인 성공후 url
		
		
	}
}
