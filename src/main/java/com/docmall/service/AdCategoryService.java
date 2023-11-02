package com.docmall.service;

import java.util.List;

import com.docmall.domain.CategoryVO;

public interface AdCategoryService {

	List<CategoryVO> getFirstCategoryList(); // 1차 카테고리 출력
	
	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);// 2차 카테고리 출력


}
