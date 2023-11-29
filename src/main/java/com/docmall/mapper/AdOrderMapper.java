package com.docmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.OrderDetailInfoVO;
import com.docmall.domain.OrderDetailProductVO;
import com.docmall.domain.OrderVO;
import com.docmall.dto.Criteria;

public interface AdOrderMapper {
	
	// 데이터의 개수에 따라 List 컬렉션 사용
	List<OrderVO> order_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	// 주문상세 1
	List<OrderDetailInfoVO> orderDetailInfo1(Long ord_code);
	
	// 주문상세 2 ─ MyBatis의 resultMap 사용(예외)
	List<OrderDetailProductVO> orderDetailInfo2(Long ord_code); // 기존 클래스 이용: OrderDetailVO, ProductVO 필드가 있는 클래스
	
	// @Param: 하나 있을 때도 사용하나, 일반적으로 생략함
	void order_product_delete(@Param("ord_code") Long ord_code, @Param("pro_num") Integer pro_num);
}