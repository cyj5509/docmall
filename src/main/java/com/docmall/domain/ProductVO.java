package com.docmall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

/*
CREATE TABLE PRODUCT_TBL(
        PRO_NUM             NUMBER  CONSTRAINT  PK_PRO_NUM         PRIMARY KEY,
        CG_CODE             NUMBER            		NULL,
        PRO_NAME            VARCHAR2(50)          	NOT NULL,
        PRO_PRICE           NUMBER                   NOT NULL,
        PRO_DISCOUNT        NUMBER                   NOT NULL,
        PRO_PUBLISHER       VARCHAR2(50)             NOT NULL,
        PRO_CONTENT         VARCHAR2(4000)           NOT NULL,      
        PRO_UP_FOLDER       VARCHAR2(50)             NOT NULL,
        PRO_IMG             VARCHAR2(100)            NOT NULL,  -- 날짜폴더경로가 포함하여 파일이름저장
        PRO_AMOUNT          NUMBER                   NOT NULL,
        PRO_BUY             CHAR(1)                  NOT NULL, -- VARCHAR(2) -> CHAR(1)
        PRO_DATE            DATE DEFAULT SYSDATE     NOT NULL,
        PRO_UPDATEDATE      DATE DEFAULT SYSDATE     NOT NULL,
        FOREIGN KEY(CG_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
);
*/

@Data
public class ProductVO {

	// pro_num, pro_up_folder, pro_img, pro_date, pro_updatedate는 직접 입력 받지 않음
	
	private Integer pro_num; // 상품코드: 시퀀스 생성(사용자로부터 직접 입력받지 않음)
	private Integer cg_code; // cg_code: 2차 카테고리 코드
	private String pro_name;
	private int pro_price;
	private int pro_discount;

	private String pro_publisher;
	private String pro_content;
	private String pro_up_folder; // 클라이언트에서 직접 입력받지 않고 스프링에서 별도로 처리(매퍼 작업)
	private String pro_img; // 클라이언트에서 직접 입력받지 않고 스프링에서 별도로 처리(매퍼 작업)
	
	private int pro_amount;
	private String pro_buy;
	
	private Date pro_date;
	private Date pro_updatedate; //직접 입력 받지 않음
	
	// private MultipartFile uploadFile; 여기서 작성하거나 컨트롤러에서 매개변수로 활용
}
