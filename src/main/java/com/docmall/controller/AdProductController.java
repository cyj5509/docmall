package com.docmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.service.AdProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/product/*")
@RequiredArgsConstructor
@Log4j
public class AdProductController {

	private final AdProductService adProductService;
	
	// 상품등록 폼
	@GetMapping("/pro_insert")
	public void pro_insert() {
		
		log.info("상품등록 폼");
	}
	
	// 1차 카테고리 테이터를 Model로 작업(설명용)
	// 반복적인 작업을 하나로 처리하기 위해 GlobalControllerAdvice 클래스를 생성하여 처리
	/*
	@GetMapping("이름")
	public void aaa(Model model) {
		
		model.addAttribute("cg_code", "1차 카테고리 정보");
	}
	*/
	
}
