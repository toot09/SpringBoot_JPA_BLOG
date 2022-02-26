package com.cos.blog.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	@GetMapping({ "", "/" })
	public String index(Model model) {
		model.addAttribute("boards", service.getBoards());
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
