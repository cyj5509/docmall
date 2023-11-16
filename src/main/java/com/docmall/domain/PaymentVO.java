package com.docmall.domain;

import java.util.Date;

import lombok.Data;

/*
CREATE TABLE payment (
		  pay_code          NUMBER PRIMARY KEY,    
		  ord_code          NUMBER NOT NULL,        
		  mbsp_ip           VARCHAR2(50) NOT NULL, 
		  pay_method        VARCHAR2(50) NOT NULL, 
		  pay_date          DATE  NULL,             
		  pay_tot_price     NUMBER NOT NULL,			
		  pay_nobank_price  NUMBER NULL,	
		  pay_rest_price    NUMBER NULL,            
		  pay_nobank_user   VARCHAR2(50) NULL,    
		  pay_noback        VARCHAR2(50) NULL,     
		  pay_memo          VARCHAR2(50) NULL          
);
*/

@Data
public class PaymentVO {

	private Integer pay_code; // 일련번호
	private Long ord_code; // 주문번호
	private String mbsp_ip; // 회원 ID
	private String pay_method; // 결제방식
	private Date pay_date; // 걀제일
	private int pay_tot_price; // 결제금액
	private int pay_nobank_price; // 무통장 입금금액
	private int pay_rest_price; // 미지급금
	private String pay_nobank_user; // 무통장 입금자명
	private String pay_noback; // 입금은행
	private String pay_memo; // 메모
	
}
