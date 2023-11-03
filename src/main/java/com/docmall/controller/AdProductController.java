package com.docmall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
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

	// 메인 및 썸네일 이미지 업로드 폴더 경로 주입 작업
	// servlet-context.xml의 beans 참조 -> <beans:bean id="uploadPath" class="java.lang.String">
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	// CKEditor에서 사용되는 업로드 폴더 경로
	@Resource(name = "uploadCKPath")
	private String uploadCKPath;
	
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
	// <input type = "file" class="form-control" name="uploadFile" id="uploadFile" placeholder="작성자 입력..."/>
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
		
		
		return "redirect:/admin/product/pro_list"; // 상품 리스트 주소로 이동
	}
	
	// CkEditor 업로드 탭에서 파일 업로드 시 동작하는 매핑주소
	// imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) 메서드
	/*
	1. HttpServletRequest 클래스: JSP의 Request 객체 클래스로, 클라이언트의 요청을 담고 있는 객체
	2. HttpServletResponse 클래스: JSP의 Response 객체 클래스로, 클라이언트로 보낼 서버 측의 응답 정보를 가지고 있는 객체
	3. MultipartFile 클래스: 업로드된 파일을 참조하는 객체(변수명과 CkEditor의 name을 일치) -> <input ~ type="file" name="upload" size="38">
	*/
	@PostMapping("/imageUpload")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		
		// 전역변수로 사용하기 위해 try~catch 코드 사용 전에 선언
		OutputStream out = null;
		PrintWriter printWriter = null; // 클라이언트에게 서버의 응답 정보를 보낼 때 사용
		
		// JSP 파일의 아래 역할과 동일
		/*
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %> 
		*/
		// 클라이언트에게 보내는 응답 설정
		response.setCharacterEncoding("utf-8");
		response.setContentType("/text/html; charser=utf-8");
		
		try {
			
			// 1) 파일 업로드 작업
			String fileName = upload.getOriginalFilename(); // 클라이언트에서 전송한 파일 이름
			byte[] bytes = uploadCKPath.getBytes(); // 업로드한 파일을 byte 배열로 읽어들임
			
			String ckUploadPath = uploadCKPath + fileName;
			
			log.info("CKEditor 파일 경로: " + ckUploadPath);
			
			out = new FileOutputStream(new File(ckUploadPath)); // 0KB 파일 생성
			out.write(bytes); // 출력 스트림 작업
			out.flush();
			
			// 2) 파일 업로드 작업 후 파일 정보를 CKEditor로 보내는 작업
			printWriter = response.getWriter();
			
			// 브라우저의 CKEditor에서 사용할 업로드한 파일 정보를 참조하는 경로 설정 작업하는 방법(두 가지)
			/*
			1. 톰캣 Context Path에서 Add External Web Module... 작업을 해야 함(그렇지 않으면 프로젝트가 커짐)
	  			-> Path는 /ckupload(임의로 설정), Document base는 C:\\Dev\\upload\\ckeditor 설정
	        2. Tomcat의 server.xml에서 <Context docBase="업로드경로" path="/매핑 주소" reloadable="true"/>
	        		-> <Context docBase="C:\\Dev\\upload\\ckeditor" path="/ckupload" reloadable="true"/>
	        		
	        	※ 설정할 때는 '\' 하나만 사용, 코드상으로는 '\\' 두 개 사용
	        	
			*/ 
			
			// CKEditor에서 업로드된 파일 경로를 보내줄 때 매핑 주소와 파일명이 포함
			String fileUrl = "/ckupload/" + fileName;
			// {"filename":"abc.gif", "uploaded":1, "url":"/upload/abc.gif"}
			printWriter.println("{\"filename\":\"" +  fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
			printWriter.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(printWriter != null) printWriter.close();
		}
	}
	
	// 상품 리스트: 목록과 페이징
	@GetMapping("/pro_list")
	public void pro_list(Criteria cri, Model model) throws Exception {
	
		List<ProductVO> pro_list = adProductService.pro_list(cri);
		
		// 날짜 폴더의 '\'를 '/'로 바꾸는 작업(이유: '\'로 되어 있는 정보가 스프링으로 보내는 요청 데이터에 사용되면 에러 발생)
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		model.addAttribute("pro_list", pro_list);
		
		int totalCount = adProductService.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	

}
