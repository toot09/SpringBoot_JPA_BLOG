package com.cos.blog.Controller.api;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/api/user")
	public ResponseDto<Integer>  joinUser(@RequestBody User user) throws Exception {
		
		return new ResponseDto<Integer>(HttpStatus.OK, service.insert(user));
	}
}
