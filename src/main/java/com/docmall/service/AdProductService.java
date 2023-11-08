package com.docmall.service;

import java.util.List;

import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;

public interface AdProductService {

	// 상품 등록
	void pro_insert(ProductVO vo);

	List<ProductVO> pro_list(Criteria cri);

	int getTotalCount(Criteria cri);

	// [방법 1]
	void pro_checked_modify1(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr);
	
	// [방법 2]
	void pro_checked_modify2(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr);
	
	ProductVO pro_edit(Integer pro_num);
	
	// 상품 수정
	void pro_edit(ProductVO vo); // 꼭 pro_edit_ok로 이름을 변경할 필요 없음

	// 상품 삭제
	void pro_delete(Integer pro_num);
}
