package com.cos.blog.handler;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;


@ControllerAdvice // 모든 @Controller 즉, 전역에서 발생할 수 있는 예외를 잡아 주는 annotation
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String handleArgumentException(IllegalArgumentException e) {
		return "<h1>"+e.getMessage()+"</h1>";		
	}
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}

	@ExceptionHandler(Exception.class)
	public String Exception(Exception e) {
		return "<h1>"+e.getMessage()+"</h1>";
	}
	
}
