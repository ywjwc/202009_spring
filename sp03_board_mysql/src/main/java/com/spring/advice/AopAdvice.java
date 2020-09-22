package com.spring.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spring.controller.BoardController;

@Component
@Aspect
public class AopAdvice {
	// BoardController 매개변수
	@Before("execution(* com.spring.controller.BoardController.*(..))")
	public void startLog(JoinPoint jp) { // 무엇을
		Logger logger = LoggerFactory.getLogger(BoardController.class);
		logger.info(jp.getSignature().getName() + " 매개변수: " + Arrays.toString(jp.getArgs()));
	}
	// DAO 매개변수
	@Before("execution(* com.spring.dao.*.*(..))")
	public void startLogDAO(JoinPoint jp) {
		System.out.println(jp.getSignature().toLongString() +":매개변수" + Arrays.toString(jp.getArgs()));
	}
		
	//메소드 반환값 출력
	@AfterReturning(pointcut="execution(* com.spring.service.*.*(..))", returning = "oj")
	public void afterLog(JoinPoint jp, Object oj) {
		if (oj != null) {
			System.out.println("--리턴값: " + jp.getSignature().toLongString());
			System.out.println(oj.toString());
		}
	}
}
