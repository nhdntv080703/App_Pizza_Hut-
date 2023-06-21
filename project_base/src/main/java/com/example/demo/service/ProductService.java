package com.example.demo.service;

import com.example.demo.dto.request.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface ProductService {
    ResponseEntity<?> createProduct(@Valid ProductDTO productDTO, BindingResult bindingResult);
    ResponseEntity<?> findAll(Integer page, Integer size );
    ResponseEntity<?> findOne(Long id);
    ResponseEntity<?> findByName(Integer page, Integer size, String nameProduct);
    ResponseEntity<?> updateProduct(Long id, ProductDTO productDTO, BindingResult bindingResult);
    void deleteProduct(Long id);
}
