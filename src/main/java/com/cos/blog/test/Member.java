package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 모든 값을 갖는 생성자 
@NoArgsConstructor //값이 없는 생성자
@Builder
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	
//	@Builder	
//	public Member(int id, String username, String password, String email) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.password = password;
//		this.email = email;
//	}
	
}
