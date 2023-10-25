package com.docmall.mapper;

public interface MemberMapper {

	/*
	<인터페이스의 사용법>

	public interface 인터페이스명{
	    (public static final) 타입 상수명 = 값;
	    (public abstract) 메서드명(매개변수 목록);
	}
	*/
	
	String idCheck(String mbsp_id);
	
}
