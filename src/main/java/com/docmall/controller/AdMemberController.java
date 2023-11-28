package com.docmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.service.AdMemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/member/*")
@Log4j
public class AdMemberController {

	private final AdMemberService adMemberService;
}
