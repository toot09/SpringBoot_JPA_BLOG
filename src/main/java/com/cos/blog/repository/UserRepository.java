package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO
// 자동으로 Bean등록 된다.
// @Repository // 생략가능 자동으로 등록된다.
public interface UserRepository extends JpaRepository<User, Integer>{
	// SELECT * FROM user WHERE username=1?;
	Optional<User> findByUsername(String username);
	
}


/* [ 참고 ] */
// JPA Naming 쿼리
// SELECT * FROM user WHERE username=? and password = ?
// 추상 메서드 이름기준으로 이렇게 쿼리를 만들어준다.
//User findByUsernameAndPassword(String username, String password);

