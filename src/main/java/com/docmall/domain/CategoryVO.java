package com.docmall.domain;

// import lombok.Data;

/*
CREATE TABLE category_tbl(
	cg_code            NUMBER    PRIMARY KEY,    -- 카테고리 코드
	cg_parent_code     NUMBER    NULL,       -- 상위카테고리 코드
	cg_name            VARCHAR2(50)    NOT NULL,
	FOREIGN KEY(cg_parent_code) REFERENCES category_tbl(cg_code)
);
*/

// @Data
public class CategoryVO {

	// 테이블의 컬럼명과 필드명을 동일하게 함
	// 동일하지 않은 경우, 1) 컬럼명 AS 별칭 또는 2) MyBatis resultMap 사용
	private Integer cg_code; // 1차든 2차든 모든 카테고리 코드
	private Integer cg_parent_code; // 1차 카테고리 코드
	private String cg_name; // 카테고리명
	
	// 소스 메뉴 단축키: [Alt]+[Shift]+[S]
	// @Getter, @Setter
	public Integer getCg_code() {
		return cg_code;
	}
	public void setCg_code(Integer cg_code) {
		this.cg_code = cg_code;
	}
	public Integer getCg_parent_code() {
		return cg_parent_code;
	}
	public void setCg_parent_code(Integer cg_parent_code) {
		this.cg_parent_code = cg_parent_code;
	}
	public String getCg_name() {
		return cg_name;
	}
	public void setCg_name(String cg_name) {
		this.cg_name = cg_name;
	}
	
	// @ToString
	@Override
	public String toString() {
		return "CategotyVO [cg_code=" + cg_code + ", cg_parent_code=" + cg_parent_code + ", cg_name=" + cg_name + "]";
	}
}
