package com.docmall.service;

import org.springframework.stereotype.Service;

import com.docmall.mapper.CartMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private final CartMapper cartMapper;
}
