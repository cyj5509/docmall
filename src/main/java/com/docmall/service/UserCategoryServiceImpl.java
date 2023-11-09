package com.docmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.domain.CategoryVO;
import com.docmall.mapper.UserCategoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

// Bean의 생성 및 등록을 위한 설정 작업
// 1. @Service: UserCategoryServiceImpl Bean 생성
// 2. root-context.xml의 <context:component-scan base-package="com.docmall.service" />
@Service 
@RequiredArgsConstructor
@Log4j
public class UserCategoryServiceImpl implements UserCategoryService {

	private final UserCategoryMapper userCategoryMapper;

	@Override
	public List<CategoryVO> getSecondCategoryList(Integer cg_parent_code) {
		
		return userCategoryMapper.getSecondCategoryList(cg_parent_code);
	}

}
