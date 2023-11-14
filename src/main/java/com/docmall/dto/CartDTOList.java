package com.docmall.dto;

import lombok.Data;

@Data
public class CartDTOList {
	
	// CartVO에서 Copy & Paste
	private Long cart_code;
	private Integer pro_num;
	private int cart_amount;
	
	// ProductVO에서 Copy & Paste
	private String pro_name;
	private int pro_price;
	private String pro_up_folder;
	private String pro_img;
	private int pro_discount;
}
