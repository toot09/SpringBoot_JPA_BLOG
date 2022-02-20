package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// 자동으로 Bean등록 된다.
// @Repository // 생략가
public interface UserRepository extends JpaRepository<User, Integer>{

}
