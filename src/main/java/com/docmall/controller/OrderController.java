package com.docmall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.docmall.domain.MemberVO;
import com.docmall.dto.CartDTOList;
import com.docmall.service.CartService;
import com.docmall.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/order/*")
@Log4j
public class OrderController {

	private final CartService cartService;
	private final OrderService orderService;
	
	// 주문 정보 페이지 이동
	@GetMapping("/order_info")
	public void order_info(HttpSession session, Model model) throws Exception {
		
		log.info("주문 정보 페이지 진입");
		
		// 주문 정보
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();

		
		// [참고] UserProductController의 @GetMapping("/pro_list")
		List<CartDTOList> order_info = cartService.cart_list(mbsp_id);
		
		double order_price = 0;

		// 날짜 폴더의 '\'를 '/'로 바꾸는 작업(이유: '\'로 되어 있는 정보가 스프링으로 보내는 요청 데이터에 사용되면 에러 발생)
		// 스프링에서 처리 안하면 자바스크립트에서 처리할 수도 있다.
		/*
		cart_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
			
			// 금액 = (판매가 - (판매가 * 할인율)) * 수량
			cart_total_price += (double) (vo.getPro_price() - (vo.getPro_price() * vo.getPro_discount() * 1/100)) * vo.getCart_amount();
		});
		*/
		
		// 위 코드는 컴파일 에러 발생해서 다음 코드로 대체함
		for (int i = 0; i < order_info.size(); i++) {
			CartDTOList vo = order_info.get(i);
			
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
			// vo.setPro_discount(vo.getPro_discount() * 1/100);
			
			order_price += (double) (vo.getPro_price() - (vo.getPro_price() * vo.getPro_discount() * 1/100)) * vo.getCart_amount();
		}
		
		model.addAttribute("order_info", order_info);
		model.addAttribute("order_price", order_price);
	}
}
