package com.docmall.service;

import com.docmall.domain.OrderVO;

public interface OrderService {

	int getOrderSeq(); // 주문번호에 사용할 시퀀스

	// 주문하기
	void order_insert(OrderVO o_vo); 

}
