package com.docmall.service;

import java.util.List;

import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;

public interface AdProductService {

	void pro_insert(ProductVO vo);

	List<ProductVO> pro_list(Criteria cri);

	int getTotalCount(Criteria cri);

	// [방법 1]
	void pro_checked_modify1(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr);
	
	// [방법 2]
	void pro_checked_modify2(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr);
	
	ProductVO pro_edit(Integer pro_num);
	
	// 상품 수정에서 1차와 2차 카테고리를 보여주는 작업
	CategoryVO get(Integer cg_code);
		
}
