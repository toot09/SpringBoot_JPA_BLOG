package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	// User 클래스가 MySQL에 테이블이 생성이 된다.
//@DynamicInsert // null 값이 있을때는 Insert 대상에서 제외한다. 이유는 default 값이 있어서 값을 안넣었더니 null insert가 되버리는 경우 방지.
public class User {

	@Id // Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에 연결된 DB의 넘버링 전략에 맞춤.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable=false, length=30)
	private String username;
	
	@Column(nullable=false, length=100)
	private String password;
	
	@Column(nullable=false, length=50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING) // EnumType이 String 이라는것을 선언.
	private RoleType role; // 권한 같은 경우 enum 객체를 만들어서 쓰면 좋다.
	
	@CreationTimestamp // 인스턴 생성될 시간 자동으로 입력
	private Timestamp createDate;
	
}
