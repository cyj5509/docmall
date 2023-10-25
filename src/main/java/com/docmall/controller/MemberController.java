package com.docmall.controller;

import org.springframework.http.HttpStatus;
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
	// 아이디 중복 체크
	// ResponseEntity 클래스? httpEntity를 상속받는, 결과 데이터와 HTTP상태코드를 직접 제어 할 수 있는 클래스이다.
	// 3가지 구성요소 ─ HttpStatus, HttpHeaders, HttpBody
	// Ajax 기능과 함께 사용
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String mbsp_id) {
		
		log.info("아이디: " + mbsp_id);
		ResponseEntity<String> entity = null;
		
		// 서비스 메서드 호출 구문 작업
		String idUse = "";
		if (memberService.idCheck(mbsp_id) != null) {
			idUse = "no"; // 아이디가 존재하여 사용 불가능
		} else {
			idUse = "yes"; // 아이디가 존재하지 않아 사용 가능
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK); // HttpStatus.OK: 태코드 200번
		
		return entity;
	}
	
}
