package com.docmall.mapper;

import org.apache.ibatis.annotations.Param;

// import com.docmall.domain.OrderDetailVO;
import com.docmall.domain.OrderVO;

public interface OrderMapper {

	int getOrderSeq(); // 주문번호에 사용할 시퀀스
	
	// <주문하기>
	// 1) 주문 테이블 저장 
	void order_insert(OrderVO o_vo); 

	// 2) 주문 상세 테이블 저장 -> 장바구니 테이블 참조 
	// void order_detail_insert(OrderDetailVO od_vo); // 해당 클래스를 받아오는 방법은 사용하지 않음
	void order_detail_insert(@Param("ord_code") Long ord_code,
							@Param("mbsp_id") String mbsp_id); // 주문 상세 테이블 저장
	
	// 결제 테이블(나중에 처리)
	
	// 3) 장바구니 테이블 삭제(비우기)
	void cart_del(String mbsp_id);
}
