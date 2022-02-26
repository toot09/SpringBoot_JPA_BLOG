package com.cos.blog.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {
	
	@GetMapping({ "", "/" })
	public String index() {
//	      prefix: /WEB-INF/views/
//	      suffix: .jsp
		return "index";
	}
	
	// USER 권한 필요 
	@GetMapping("/board/saveForm")
	public String SaveForm() {
		return "board/saveForm";
	}
	
}
