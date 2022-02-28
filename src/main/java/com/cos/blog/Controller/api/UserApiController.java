package com.cos.blog.Controller.api;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService service;

	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) throws Exception {
		service.insert(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	@PutMapping("/auth/updateProc")
	public ResponseDto<Integer> update(@RequestBody User user) throws Exception {
		service.update(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}