package com.example.demo.service;

import com.example.demo.dto.request.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface CategoryService {
    ResponseEntity<?> createCategory(@Valid CategoryDTO categoryDTO, BindingResult bindingResult);
    ResponseEntity<?> findAll(Integer page, Integer size );
    ResponseEntity<?> findOne(Long id);
    ResponseEntity<?> findByName(Integer page, Integer size, String nameProduct);
    ResponseEntity<?> updateCategory(Long id, CategoryDTO categoryDTO, BindingResult bindingResult);
    void deleteCategory(Long id);
}
