package com.docmall.service;

import java.util.List;

import com.docmall.domain.CartVO;
import com.docmall.dto.CartDTOList;

public interface CartService {

	void cart_add(CartVO vo); // 장바구니 추가 관련 메서드

	// 상품 테이블과 장바구니 테이블의 조인 작업으로 CartDTOList 활용
	// List<CartDTOList>: CartVO에서 여러 개의 데이터를 가져올 경우
	List<CartDTOList> cart_list(String mbsp_id); // 장바구니 목록 관련 메서드
	
	// 장바구니 수량 변경 관련 메서드
	void cart_amount_change(Long cart_code, int cart_amount);

	// 장바구니 목록에서 개별 삭제 관련 메서드
	void cart_list_del(Long cart_code);

	// 장바구니 목록에서 선택 삭제 관련 메서드
	void cart_sel_delete(List<Long> cart_code_arr);
}
