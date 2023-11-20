package com.docmall.domain;

import java.util.Date;

import lombok.Data;

/*
CREATE TABLE admin_tbl (
    admin_id    VARCHAR2(15)    PRIMARY KEY,
    admin_pw    CHAR(60)    NOT NULL,
    admin_visit_date    DATE
);
*/

@Data
public class AdminVO {

	private String admin_id;
	private String admin_pw;
	private Date admin_visit_date;
}
