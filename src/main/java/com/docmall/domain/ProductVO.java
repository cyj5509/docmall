package com.docmall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVO {

/*
CREATE TABLE PRODUCT_TBL(
        PRO_NUM             NUMBER  CONSTRAINT  PK_PRO_NUM         PRIMARY KEY,
        CG_CODE             NUMBER            		NULL,
        PRO_NAME            VARCHAR2(50)          	NOT NULL,
        PRO_PRICE           NUMBER                   NOT NULL,
        PRO_DISCOUNT        NUMBER                   NOT NULL,
        PRO_PUBLISHER       VARCHAR2(50)             NOT NULL,
        PRO_CONTENT         VARCHAR2(4000)           NOT NULL,       -- 내용이 4000BYTE 초과여부판단?
        PRO_UP_FOLDER       VARCHAR2(50)             NOT NULL,
        PRO_IMG             VARCHAR2(100)            NOT NULL,  -- 날짜폴더경로가 포함하여 파일이름저장
        PRO_AMOUNT          NUMBER                   NOT NULL,
        PRO_BUY             CHAR(1)                  NOT NULL, -- VARCHAR(2) -> CHAR(1)
        PRO_DATE            DATE DEFAULT SYSDATE     NOT NULL,
        PRO_UPDATEDATE      DATE DEFAULT SYSDATE     NOT NULL,
        FOREIGN KEY(CG_CODE) REFERENCES CATEGORY_TBL(CG_CODE)
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
