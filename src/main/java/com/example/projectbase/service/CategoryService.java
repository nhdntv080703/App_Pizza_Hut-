package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.CategoryCreateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface CategoryService {
    ResponseEntity<?> createCategory(@Valid CategoryCreateDTO categoryDTO, BindingResult bindingResult);
    ResponseEntity<?> findAll(Integer page, Integer size );
    ResponseEntity<?> findOne(Long id);
    ResponseEntity<?> findByName(Integer page, Integer size, String nameProduct);
    ResponseEntity<?> updateCategory(Long id, CategoryCreateDTO categoryDTO, BindingResult bindingResult);
    void deleteCategory(Long id);
}
