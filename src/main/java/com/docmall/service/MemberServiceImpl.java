package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.domain.MemberVO;
import com.docmall.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

	// 자동 주입 ─ @RequiredArgsConstructor
	// memberMapper 필드를 매개변수로 하는 생성자 메서드가 생성
	private final MemberMapper memberMapper;

	// @RequiredArgsConstructor와 private final~은 아래와 동일함
	/*
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	*/
	
	@Override
	public String idCheck(String mbsp_id) {
	
		return memberMapper.idCheck(mbsp_id);
	}

	@Override
	public void join(MemberVO vo) {

		memberMapper.join(vo);
	}

	@Override
	public MemberVO login(String mbsp_id) {

		return memberMapper.login(mbsp_id);
	}

}
