package com.docmall.service;

import java.util.List;

import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;

public interface UserProductService {

	// 2차 카테고리별 상품 리스트(페이징 정보 사용, 검색 제외)
	List<ProductVO> pro_list(Integer cg_code, Criteria cri);
	
	int getTotalCount(Integer cg_code);
}
