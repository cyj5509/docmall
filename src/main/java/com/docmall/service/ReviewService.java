package com.docmall.service;

import java.util.List;

import com.docmall.domain.ReviewVO;
import com.docmall.dto.Criteria;

public interface ReviewService {

	void review_insert(ReviewVO vo);

	List<ReviewVO> list(Integer pro_num, Criteria cri); // Criteria에서 검색 관련 필드 사용 안함
	
	int listCount(Integer pro_num);
	
	void delete(Long rew_num); // INSERT, DELETE, UPDATE 문은 void
}
