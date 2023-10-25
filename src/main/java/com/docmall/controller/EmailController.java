package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docmall.dto.EmailDTO;

import lombok.extern.log4j.Log4j;

@RestController // 컨트롤러 클래스가 Ajax 용도로만 사용할 때 적용하는 어노테이션 ↔ @Controller
@RequestMapping("/email/*") // 현재 JSP는 사용하지 않을 예정
@Log4j
public class EmailController {

	// 메일 인증코드 요청주소
	@GetMapping("/authcode")
	public ResponseEntity<String> authSend(EmailDTO dto, HttpSession session) {

		log.info("전자우편 정보: " + dto);
		ResponseEntity<String> entity = null;

		// 인증코드 6자리 랜덤 생성
		String authCode = "";
		for (int i = 0; i < 6; i++) {
			authCode += String.valueOf((int) (Math.random() * 10));
		}
		log.info("인증코드: " + authCode); 

		return entity;
	}
}
