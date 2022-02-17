package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome");
		// 파일리턴 기본경로 : src/main/resource/static
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		System.out.println("tempJsp");
		// 파일리턴 기본경로 : src/main/resource/static
		return "home"; 
	}
	
}
