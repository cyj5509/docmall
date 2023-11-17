package com.docmall.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docmall.domain.MemberVO;
import com.docmall.domain.OrderVO;
import com.docmall.dto.CartDTOList;
import com.docmall.kakaopay.ApproveResponse;
import com.docmall.kakaopay.ReadyResponse;
import com.docmall.service.CartService;
import com.docmall.service.KakaoPayServiceImpl;
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
	private final KakaoPayServiceImpl kakaoPayServiceImpl;
	
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
	
	// 주문 정보 페이지에서 카카오페이 결제 선택을 진행한 경우: 주문 정보, 주문 상세 정보, 결제 정보가 한꺼번에 들어옴
	// 1) 결제 준비 요청
	@GetMapping(value = "/orderPay", produces = "application/json")
	public @ResponseBody ReadyResponse payReady(String paymethod, OrderVO o_vo, /* OrderDetailVO od_vo, PaymentVO p_vo, */
											   int totalprice, HttpSession session) throws Exception {
		/*
		   1) 주문정보 구성 
		   - 주문 테이블(OrderVO): ord_status, payment_status 정보 존재하지 않음
		   - 주문 상세 테이블(OrderDetailVO): 장바구니 테이블에서 데이터를 참조하는 방법 이용
		   - 결제 테이블: 보류
		   2) 카카오페이 결제에 필요한 정보구성
		   - 스프링에서 처리할 수 있는 부분
		*/
		
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();;
		o_vo.setMbsp_id(mbsp_id); // 아이디 값 할당(설정)
		

		// 시퀀스를 주문번호로 사용: 동일한 주문번호 값이 사용
		// int com.docmall.service.OrderService.getOrderSeq()
		Long ord_code = (long) orderService.getOrderSeq();
		o_vo.setOrd_code(ord_code); // 주문번호 저장		
		
		log.info("결제방법: " + paymethod);
		log.info("주문정보: " + o_vo);
	
		// 1) 주문 테이블 저장 작업: ord_status, payment_status 데이터 준비할 것(우선은 누락시킴)
		// 2) 주문 상세 테이블 저장 작업
		
		o_vo.setOrd_status("주문완료");
		o_vo.setPayment_status("결제완료");
		
		List<CartDTOList> cart_list = cartService.cart_list(mbsp_id);
		String itemName = cart_list.get(0).getPro_name() + "외 " + String.valueOf(cart_list.size() - 1) + "건";
		
		orderService.order_insert(o_vo); // 주문, 주문상세 정보 저장, 장바구니 삭제
		
		// 3) Kakao Pay 호출 -> 1) 결제 준비 요청
		ReadyResponse readyResponse = kakaoPayServiceImpl.payReady(o_vo.getOrd_code(), mbsp_id, itemName, cart_list.size(), totalprice);
		
		log.info("결제 고유번호: " + readyResponse.getTid());
		log.info("결제 요청 URL: " + readyResponse.getNext_redirect_pc_url());
	
		// 카카오페이 결제 승인 요청 작업에 필요한 정보 준비
		session.setAttribute("tid", readyResponse.getTid());
		session.setAttribute("odr_code", o_vo.getOrd_code());
		
		return readyResponse;
	}
	
	// 결제 승인 요청 작업 -> http://localhost:8080/user/order/orderComplete
	@GetMapping("/orderApproval")
	public String orderApproval(@RequestParam("pg_token") String pg_token, HttpSession session) {
		
		// 2) Kakao Pay 결제 승인 요청 작업
		String tid = (String) session.getAttribute("tid");
		Long odr_code = (Long) session.getAttribute("odr_code");
		String mbsp_id = ((MemberVO) session.getAttribute("loginStatus")).getMbsp_id();;

		ApproveResponse approveResponse = kakaoPayServiceImpl.payApprove(tid, odr_code, mbsp_id, pg_token);

		session.removeAttribute("tid");
		session.removeAttribute("odr_code");
		
		return "redirect:/user/order/orderComplete";
	}
	
	// 결제 완료 페이지: 
	@GetMapping("/orderComplete")
	public void orderComplete() {
		
	}
	
	// 결제 취소 시: http://localhost:8080/user/order/orderCancel
	@GetMapping("/orderCancel")
	public void orderCancel() {
		
	}
}
