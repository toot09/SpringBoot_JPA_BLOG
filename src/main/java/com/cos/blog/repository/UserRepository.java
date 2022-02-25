package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// 자동으로 Bean등록 된다.
// @Repository // 생략가
public interface UserRepository extends JpaRepository<User, Integer>{

}


/* [ 참고 ] */
// JPA Naming 쿼리
// SELECT * FROM user WHERE username=? and password = ?
// 추상 메서드 이름기준으로 이렇게 쿼리를 만들어준다.
//User findByUsernameAndPassword(String username, String password);
