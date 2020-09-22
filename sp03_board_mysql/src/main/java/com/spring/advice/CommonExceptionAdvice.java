package com.spring.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 컨트롤러에서 발생하는 예외를 전문적으로 처리하는 클래스
@ControllerAdvice
public class CommonExceptionAdvice {

	@ExceptionHandler(Exception.class)
	public String common(Exception e, Model model) {
		System.out.println("예외발생");
		model.addAttribute("exception", e);
		return "error_common";
	}
}