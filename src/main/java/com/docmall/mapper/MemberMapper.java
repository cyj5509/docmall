package com.docmall.mapper;

import com.docmall.domain.MemberVO;

public interface MemberMapper {

	/*
	<인터페이스의 사용법>

	public interface 인터페이스명{
	    (public static final) 타입 상수명 = 값;
	    (public abstract) 메서드명(매개변수 목록);
	}
	*/
	
	String idCheck(String mbsp_id);
	
	void join(MemberVO vo);
	
	// 로그인을 위해 회원정보 전체가 아닌 아이디만 가져옴
	MemberVO login(String mbsp_id);
}
