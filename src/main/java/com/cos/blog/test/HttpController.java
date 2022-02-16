package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML)
//@Controller 

// 사용자가 요청 -> 응답(data)
@RestController
public class HttpController {

	@GetMapping("/http/lombok")
	public String lombokTest() {
		// AllArgsConstructor 사용 
		//Member m = new Member(1, "UJONE", "1234", "GMAIL");
		// Builder 사용 
		Member m = Member.builder().email("GMAIL").id(0).password("1234").username("IAM").build();
		System.out.println("id : "+m.getId());
		m.setId(2);
		System.out.println("id : "+m.getId());
		return "lombok test 완료";
	}
	
	// http://localhost:8080/http/get (seletct)
	@GetMapping("/http/get")
	public String getTest(Member m) { //@RequestParam int id
		//return "get 요청 id : "+id+" username : "+username;
		return "post 요청 id : "+m.getId()+" username : "+m.getUsername()+" password : "+m.getPassword()+" email : "+m.getEmail();
	}
	
	// http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // MessageConverter (스프링부트) 에서 param형식에 맞게 딱딱 맞춰준다.
		return "post 요청 id : "+m.getId()+" username : "+m.getUsername()+" password : "+m.getPassword()+" email : "+m.getEmail();
	}
	
	// http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 id : "+m.getId()+" username : "+m.getUsername()+" password : "+m.getPassword()+" email : "+m.getEmail();
	}
	
	// http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody Member m) {
		return "delete 요청 id : "+m.getId()+" username : "+m.getUsername()+" password : "+m.getPassword()+" email : "+m.getEmail();
	}
}
