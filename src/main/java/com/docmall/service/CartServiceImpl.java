package com.docmall.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.docmall.domain.CartVO;
import com.docmall.dto.CartDTOList;
import com.docmall.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartMapper cartMapper; // 장바구니 추가 관련 메서드

	@Override
	public void cart_add(CartVO vo) {
		
		cartMapper.cart_add(vo);
	}

	@Override
	public List<CartDTOList> cart_list(String mbsp_id) {
	
		return cartMapper.cart_list(mbsp_id);
	}

	@Override
	public void cart_amount_change(Long cart_code, int cart_amount) {
		
		cartMapper.cart_amount_change(cart_code, cart_amount);
	}



}
