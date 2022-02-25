package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	@Transactional
	public void insert (User user) {
		user.setRole(RoleType.USER);
		System.out.println("Endcode 전 : "+user.getPassword());
		user.setPassword(encode.encode(user.getPassword()));
		System.out.println("Endcode  : "+user.getPassword());
		userRepository.save(user);
	}
	
}
