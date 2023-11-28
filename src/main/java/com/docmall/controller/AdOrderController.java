package com.docmall.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.OrderDetailVO;
import com.docmall.domain.OrderVO;
import com.docmall.domain.ReviewVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.PageDTO;
import com.docmall.service.AdOrderService;
import com.docmall.util.FileUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/order/*")
@Log4j
public class AdOrderController {

	private final AdOrderService adOrderService;

	// 메인 및 썸네일 이미지 업로드 폴더 경로 주입 작업
	// servlet-context.xml의 beans 참조 -> <beans:bean id="uploadPath" class="java.lang.String">
	@Resource(name = "uploadPath")
	private String uploadPath;

	// 주문 리스트: 목록과 페이징
	// 테이블의 전체 데이터를 가져옴
	@GetMapping("/order_list")
	public void pro_list(Criteria cri, Model model) throws Exception {

		// 10 -> 2로 변경
		cri.setAmount(2); // Criteria에서 this(1, 2);

		List<OrderVO> order_list = adOrderService.order_list(cri);
		model.addAttribute("order_list", order_list);

		int totalCount = adOrderService.getTotalCount(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}

	
	// ReviewContorll에서 Copy & Paste
	// 전통적인 형태의 주소 list?pro_num=10&page=1 -> REST API 개발형태 주소 list/10/1
	// ResponseEntity<String>는 AJAX 요청 시 SELECT 외 나머지, AJAX 요청 시 SELECT면 해당하는 리턴 타입 필요
	@GetMapping("/order_detail_info/{ord_code}") // RESTful 개발방법론의 주소
	public ResponseEntity<List<OrderDetailVO>> list(@PathVariable("pro_num") Integer pro_num,
			@PathVariable("page") Integer page) throws Exception {

		// 클래스명은 주문 상세 테이블과 상품 테이블을 조인한 결과만 담는 클래스

		ResponseEntity<List<OrderDetailVO>> entity = null;

		return entity;
	}
	
	// 상품 리스트에서 보여줄 이미지. <img src="매핑주소">
	@ResponseBody
	@GetMapping("/imageDisplay") // /admin/product/imageDisplay?dateFolderName=값1&fileName=값2
	public ResponseEntity<byte[]> imageDisplay(String dateFolderName, String fileName) throws Exception {

		return FileUtils.getFile(uploadPath + dateFolderName, fileName);
	}

}
