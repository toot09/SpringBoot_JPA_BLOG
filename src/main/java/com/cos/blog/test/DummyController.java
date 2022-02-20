package com.cos.blog.test;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyController {

	// 선언한 UserRepositor는 JpaRepository를 상속받는 인터페이스 DAO의 역할 즉, 데이터 CRUD가능.
	// UserRepository의 JpaRepository는 IoC를 통해 메모리에 떠있기 때문에 Autowired로 사용가능(DI).
	@Autowired
	private UserRepository userRepository;
	
	// {id}주소로 파라미터 전달 받을 수 있음.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// findById의 형태는 Optional이다.
		// 왜냐?아이디 값으로 조회했는데null값이면 빈 객체로 인해 오류 발생할 수 있으니
		// Optional 값으로 줄테니 니가 알아서 걸러서 써
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>(){
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. ID: "+id);
			}
		});
// 		Lambda
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다. ID: "+id);
//		});
		
		// Spring boot의 MessageConverter	가Jackson 라이브러리 호출해서 json 형식으로 자동 리턴시켜준다.
		return user;
	}
	
	@PostMapping("/dummy/join")
	public String join(User user) {
		
		// Default 세팅.
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		return "Join test is done";
	}
	
}
