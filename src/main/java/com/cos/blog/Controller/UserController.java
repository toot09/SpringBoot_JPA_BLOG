package com.cos.blog.Controller;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.config.ApiConfig;
import com.cos.blog.model.OAuthToken;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
	[인증이 안된 사용자들이 출입할 수 있는 경로]
	1. /auth/* 로 통일 시킨다.
	2. 그냥 주소가 / 이면 index.jsp 허용
	3. static 이하에 있는 /js/*, /css/*, /image/* 허용 
*/

@Controller
public class UserController {
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm(Model model) {
		
		model.addAttribute("KEY", ApiConfig.KAKAO_OAUTH_KEY.getContent());
		model.addAttribute("CALLBACK", ApiConfig.KAKAO_OAUTH_CALLBACK.getContent());
		
		return "user/loginForm";
	}
	
	// KAKAO OAUTH 로그인
	@GetMapping("/auth/kakao/callback")
	// Data를 리턴해주는 함수 (예를 들어 String 리턴해주면 화면에 String 띄워준다. 그니까 리턴을 말그대로 Data로 해줌)
	public @ResponseBody String kakaoCallback(String code) {
		
		// POST방식으로 key=value 데이터를 카카오로 요청하여 토큰 발급 
		// Retrofit2
		// OkHttp
		// RestTemplate
		RestTemplate rt = new RestTemplate();
		
		// 해더 만들기 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		// 바디 만들기 (HashMap 사용 불가!)
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", ApiConfig.KAKAO_OAUTH_KEY.getContent());
		params.add("redirect_uri", ApiConfig.KAKAO_OAUTH_CALLBACK.getContent());
		params.add("code", code);
		
		
		// 해더와 바디를 하나의 오브젝트로 만들기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
				new HttpEntity<>(params, headers);
		
		// Http 요청하고 리턴값을 response 변수로 받기
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class);
		
		// JSON 데이터 Object에 담기. (라이브러리 : Gson, Json Simple, ObjectMapper)
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken = null;
		try {
			// OAuthToken는 내가 만든 Class이며 이에 맞게 JSON을 객체화 한다.
			oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		// 일단 카카오 로그인 OAuth2.0은 스
		
		return response.getBody();
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
}
