package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.CartCreateDTO;
import com.example.projectbase.domain.dto.response.CartResponseDTO;

import java.util.List;

public interface CartService {
    CartResponseDTO create(CartCreateDTO cartCreateDTO);
    void delete(long id);
    List<CartResponseDTO> findAll();
}
