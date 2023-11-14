package com.docmall.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartVO {

/*
CREATE TABLE CART_TBL(
        CART_CODE        NUMBER,
        PRO_NUM         NUMBER          NOT NULL,
        MBSP_ID         VARCHAR2(15)    NOT NULL,
        CART_AMOUNT      NUMBER          NOT NULL,
        FOREIGN KEY(PRO_NUM) REFERENCES PRODUCT_TBL(PRO_NUM),
        FOREIGN KEY(MBSP_ID) REFERENCES MBSP_TBL(MBSP_ID),
        CONSTRAINT PK_CART_CODE primary key(CART_CODE) 
);
*/
	
	private Long cart_code; // 시퀀스를 통해 처리. 그 외 나머지는 Ajax 처리(pro_list.jsp 참고)
	
	private Integer pro_num; // 상품코드
	private String mbsp_id; // 세션 활용
	
	private int cart_amount;
}
