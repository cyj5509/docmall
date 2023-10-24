package com.docmall.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	
	// SQL Developer에서 구문 Copy & Paste 후 작업
	
	// 멤버 필드 
	private String mbsp_id;
	private String mbsp_name;
	private String mbsp_email;
	private String mbsp_password;
	private String mbsp_zipcode;
	private String mbsp_addr;
	private String mbsp_deaddr;
	private String mbsp_phone;
	
	private int mbsp_point;

	private Date mbsp_lastlogin, mbsp_datesub, mbsp_updatedate;
	
}
