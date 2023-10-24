package com.docmall.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
@Log4j
public class MemberController {
	
	// [참고 1] https://dev-coco.tistory.com/70
	// [참고 2] https://azderica.github.io/00-spring-injection/
	
	// 자동 주입 ─ @RequiredArgsConstructor
	// memberService 필드를 매개변수로 하는 생성자 메서드가 생성
	private final MemberService memberService;

	// 1. 회원가입 페이지 구현
	@GetMapping("/join")
	public void join() {
		
		log.info("called...join");
	}

	// Ajax 문법(비동기 방식)으로 호출
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String mbsp_id) {
		
		log.info("아이디: " + mbsp_id);
		ResponseEntity<String> entity = null;
		
		// 서비스 메서드 호출 구문 작업
		// memberService.idCheck(mbsp_id); ?? 
		
		return entity;
	}
	
}
