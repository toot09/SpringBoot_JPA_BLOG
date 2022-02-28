package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.config.SecurityConfig;
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
		user.setPassword(encode.encode(user.getPassword()));
		user.setPassword(user.getPassword());		
		userRepository.save(user);
	}
	
	@Transactional
	public void update(User mod) {
		User user = userRepository.findById(mod.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 정보가 없습니다.");
		});
		user.setPassword(encode.encode(mod.getPassword()));
		user.setEmail(mod.getEmail());
		
		// flush
	}
	
}
