package com.docmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.docmall.domain.CategoryVO;
import com.docmall.domain.ProductVO;
import com.docmall.dto.Criteria;
import com.docmall.dto.ProductDTO;

public interface AdProductMapper {

	void pro_insert(ProductVO vo);
	
	List<ProductVO> pro_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	// [방법 1]
	// @Param(""): 파라미터가 2개 이상 사용되는 경우 해당 어노테이션 필수(Mapper에서만 쓰임)
	void pro_checked_modify1(
			@Param("pro_num") Integer pro_num, // @Param("A") Integer pro_num -> #{A} 
			@Param("pro_price") Integer pro_price,
			@Param("pro_buy") String pro_buy			
	);
	
	// [방법 2]
	void pro_checked_modify2(List<ProductDTO> pro_modify_list);
	
	ProductVO pro_edit(Integer pro_num);
	
	// 상품 수정에서 1차와 2차 카테고리를 보여주는 작업
	CategoryVO get(Integer cg_code);
	
}
