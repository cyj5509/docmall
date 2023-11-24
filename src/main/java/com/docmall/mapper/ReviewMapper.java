package com.docmall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.docmall.domain.ReviewVO;
import com.docmall.dto.Criteria;

public interface ReviewMapper {

	void review_insert(ReviewVO vo);
	
	// 가져오는 데이터가 여러 개면 List, 하나면 해당 데이터 타입
	List<ReviewVO> list(@Param("pro_num") Integer pro_num, 
					   @Param("cri") Criteria cri); // Criteria에서 검색 관련 필드 사용 안함
	
	int listCount(Integer pro_num);
	
	void delete(Long rew_num); // INSERT, DELETE, UPDATE 문은 void
}
