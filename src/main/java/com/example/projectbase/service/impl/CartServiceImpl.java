package com.example.projectbase.service.impl;

import com.example.projectbase.converter.CartConvert;
import com.example.projectbase.domain.dto.request.CartCreateDTO;
import com.example.projectbase.domain.dto.response.CartResponseDTO;
import com.example.projectbase.domain.entity.CartEntity;
import com.example.projectbase.repository.CartRepository;
import com.example.projectbase.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartConvert cartConvert;
    @Override
    public CartResponseDTO create(CartCreateDTO cartCreateDTO) {
        CartEntity cartEntity=cartConvert.converDTOToEntity(cartCreateDTO);
        return cartConvert.convertEntityToDTO(cartRepository.save(cartEntity));
    }

    @Override
    public void delete(long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<CartResponseDTO> findAll() {
        return cartConvert.convertListEntityToListDTO(cartRepository.findAll());
    }
}
