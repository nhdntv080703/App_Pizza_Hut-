package com.example.projectbase.service;

import com.example.projectbase.domain.dto.CartDto;
import com.example.projectbase.domain.entity.CartEntity;

import java.util.List;

public interface CartService {
    public CartEntity create(CartDto cartDto);
    public void deleteById(Long id);
    public List<CartEntity> findAll();
}
