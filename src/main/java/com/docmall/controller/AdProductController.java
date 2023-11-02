package com.docmall.controller;

import javax.annotation.Resource;

// import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.ProductVO;
import com.docmall.service.AdProductService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/admin/product/*")
@RequiredArgsConstructor
@Log4j
public class AdProductController {

	private final AdProductService adProductService;

	// 업로드 폴더 경로 주입 작업
	@Resource(name = "uploadPath") // servlet-context.xml의 beans 참조를 해야 함
	private String uploadPath;
	
	// 상품등록 폼
	@GetMapping("/pro_insert")
	public void pro_insert() {

		log.info("상품등록 폼");
	}

	// 1차 카테고리 테이터를 Model로 작업(설명용)
	// 반복적인 작업을 하나로 처리하기 위해 GlobalControllerAdvice 클래스를 생성하여 처리
	/*
	 * @GetMapping("이름") public void aaa(Model model) {
	 * 
	 * model.addAttribute("cg_code", "1차 카테고리 정보"); }
	 */

	// 상품정보 저장 작업
	// 파일 업로드 기능
	// 1) 스프링에서 내장된 기본 라이브러리 -> servlet-context.xml에서 MultipartFile에 대한 bean 등록 작업
	// 2) 외부 라이브러리를 이용(pom.xml의 commons-fileupload) -> servlet-context.xml에서 MultipartFile에 대한 bean 등록 작업
	// 매개변수로 쓰인 MultipartFile uploadFile은 ProductVO에 직접 집어 넣어서 사용해도 됨
	// <input type = "file"class="form-control"name="uploadFile"id="uploadFile"placeholder="작성자 입력..."/>
	@PostMapping("/pro_insert")
	// public String pro_insert(ProductVO vo, List<MultipartFile> uploadFile) { // 파일이 여러 개일 때
	public String pro_insert(ProductVO vo, MultipartFile uploadFile, RedirectAttributes rttr) { // 파일이 하나일 때
		
		log.info("상품정보: " + vo);
		
		// 1) 파일 업로드 작업(선수 작업: FileUtils 클래스 작업)
		String dateFolder = FileUtils.getDateFolder();
		String savedFileName = FileUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setPro_img(savedFileName);
		vo.setPro_up_folder(dateFolder);
		
		// 2) 상품 정보 작업
		adProductService.pro_insert(vo);
		
		
		return "redirect:/리스트";
	}

}
