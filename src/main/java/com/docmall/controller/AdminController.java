package com.docmall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.AdminVO;
import com.docmall.service.AdminService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

// AdminController adminController = new AdminController();
@Controller // 클라이언트의 요청을 담당하는 기능 -> bean으로 생성 및 등록: adminController
@RequestMapping("/admin/*")
@RequiredArgsConstructor
@Log4j
public class AdminController {

	private final AdminService adminService;
	
	// 관리자 로그인 폼 페이지
	@GetMapping("")
	public String adminLogin() {
		log.info("관리자 로그인 페이지");
		
		return "/admin/adLogin";
	}
	
	// 관리자 로그인 인증
	@PostMapping("/admin_ok")
	public String admin_ok(AdminVO vo, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		return "redirect:/admin_menu";
	}
	
	// 관리자 메뉴 페이지
	@GetMapping("/admin_menu")
	public void admin_menu() {
		
	}
	
	
	
}
