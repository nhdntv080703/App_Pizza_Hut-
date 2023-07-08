package com.example.projectbase.controller;

import com.example.projectbase.base.RestApiV1;
import com.example.projectbase.base.VsResponseUtil;

import com.example.projectbase.domain.dto.CartDto;
import com.example.projectbase.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestApiV1
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("cart/create")
    public ResponseEntity<?> create(@RequestBody CartDto cartDto){
        return VsResponseUtil.success(cartService.create(cartDto));
    }
}
