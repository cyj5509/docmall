package com.docmall.mapper;

import java.util.List;

import com.docmall.domain.CategoryVO;

// 인터페이스가 Bean으로 생성되는 것이 아니라 아래 Proxy Class가 객체(Bean)로 생성되어 AdCategoryServiceImpl 클래스 안에 주입(DI)이 진행된다.
// 매퍼 클래스를 상속받는 프록시(Proxy)라는 클래스가 생성되고, 객체 생성이 이루어진다.
public interface AdCategoryMapper {

	List<CategoryVO> getFirstCategoryList(); // 1차 카테고리 출력
	
	List<CategoryVO> getSecondCategoryList(Integer cg_parent_code);// 2차 카테고리 출력
	
	// 상품 수정에서 1차와 2차 카테고리를 보여주는 작업
	CategoryVO get(Integer cg_code);
}
