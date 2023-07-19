package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.ComboCreateDTO;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface ComboService {
    ResponseEntity<?> createCombo(@Valid ComboCreateDTO comboCreateDTO, BindingResult bindingResult);
    ResponseEntity<?> findOne(Long id);
    ResponseEntity<?> findAll();
    void deleteCombo(Long id);


}
