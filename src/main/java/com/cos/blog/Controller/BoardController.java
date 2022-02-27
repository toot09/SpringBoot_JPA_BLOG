package com.cos.blog.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService service;

	@GetMapping({"", "/" })
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		
//		Page<Board> pagingBoard = service.getBoards(pageable);
//		List<Board> boards = pagingBoard.getContent();
		
		// Page 객체로 리턴받아 처리한다. 
		model.addAttribute("boards", service.getBoards(pageable));
		
//	      prefix: /WEB-INF/views/
//	      suffix: .jsp
		return "index";
	}

	// USER 권한 필요
	@GetMapping("/board/saveForm")
	public String SaveForm() {
		return "board/saveForm";
	}
	
	 @GetMapping("/board/{id}")
	 public String getBoard(Model model, @PathVariable int id) {
		 model.addAttribute("board", service.findById(id));
		 return "board/detail";
	 }

}
