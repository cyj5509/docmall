package com.docmall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVO {

		/*
		CREATE TABLE product_tbl(
	        pro_num             NUMBER  CONSTRAINT  pk_pro_num         PRIMARY KEY,
	        cg_code             NUMBER            NULL,
	        pro_name            VARCHAR2(50)            NOT NULL,
	        pro_price           NUMBER                  NOT NULL,
	        pro_discount        NUMBER                  NOT NULL,
	        pro_publisher       VARCHAR2(50)            NOT NULL,
	        pro_content         VARCHAR2(4000)  (CLOB)  NOT NULL,    
	        pro_up_folder       VARCHAR2(50)             NOT NULL,
	        pro_img             VARCHAR2(50)             NOT NULL,  
	        pro_amount          NUMBER                  NOT NULL,
	        pro_buy             CHAR(1)                 NOT NULL,
	        pro_date            DATE DEFAULT sysdate    NOT NULL,
	        pro_updatedate      DATE DEFAULT sysdate    NOT NULL,
	        FOREIGN KEY(cg_code) REFERENCES category_tbl(cg_code)
        );
		*/
	
	// pro_nun, pro_up_folder, pro_img, pro_date, pro_updatedate는 직접 입력 받지 않음
	
	private Integer pro_num, cg_code; // pro_num: 시퀀스 생성(사용자로부터 직접 입력받지 않음), cg_code: 2차 카테고리 코드
	private String pro_name;
	private int pro_price, pro_discount;
	// pro_up_folder, pro_img: 클라이언트에서 직접 입력받지 않고 스프링에서 별도로 처리(매퍼 작업)
	private String pro_publisher, pro_content, pro_up_folder, pro_img;
	private int pro_amount;
	private String pro_buy;
	private Date pro_date, pro_updatedate; // pro_date, pro_updatedate: 직접 입력 받지 않음
	
	// private MultipartFile uploadFile; 여기서 작성거나 컨트롤러에서 매개변수로 활용
}