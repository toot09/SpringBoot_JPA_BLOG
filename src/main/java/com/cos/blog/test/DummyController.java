package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.swing.SortOrder;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@RestController
public class DummyController {

	// 선언한 UserRepositor는 JpaRepository를 상속받는 인터페이스 DAO의 역할 즉, 데이터 CRUD가능.
	// UserRepository의 JpaRepository는 IoC를 통해 메모리에 떠있기 때문에 Autowired로 사용가능(DI).
	@Autowired
	private UserRepository userRepository;

	@Transactional
	@PutMapping("/dummy/user/{id}")
	public String userUpdate(@PathVariable int id, @RequestBody User requestUser) {
		
//		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
//			@Override
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("없음");
//			}
//		});
		

		User user = userdetail(id);
		
		user.setEmail(requestUser.getEmail());
		user.setPassword(requestUser.getPassword());

		// id 값을 전달하면 update
		// id 값이 없다면 insert
		//userRepository.save(user);
		
		return "Update 완료";
	}
	
	
	@GetMapping("/dummy/user")
	public List<User> userPage(@PageableDefault(size=2, sort="id") Pageable pageable) {
		
		// Page는 isLast, pageSize등 페이징 처리에 사용되는 값이 있기 때문에 사용할 수 있음.
		Page<User> page = userRepository.findAll(pageable);
		
		// Content 만 사용거면 List형으로 getContent로 사
		List<User> users = page.getContent();
		
		return users;
	}
	
	@ GetMapping("/dummy/user/all")
	public List<User> userAll() {
		return userRepository.findAll();
	}
	
	// {id}주소로 파라미터 전달 받을 수 있음.
	@GetMapping("/dummy/user/{id}")
	public User userdetail(@PathVariable int id) {
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
