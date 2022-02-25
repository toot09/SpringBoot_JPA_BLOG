package com.cos.blog.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
	[인증이 안된 사용자들이 출입할 수 있는 경로]
	1. /auth/* 로 통일 시킨다.
	2. 그냥 주소가 / 이면 index.jsp 허용
	3. static 이하에 있는 /js/*, /css/*, /image/* 허용 
*/

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
}
