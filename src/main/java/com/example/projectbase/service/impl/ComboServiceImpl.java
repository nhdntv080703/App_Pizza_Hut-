package com.example.projectbase.service.impl;

import com.example.projectbase.converter.ComboConverter;
import com.example.projectbase.domain.dto.request.ComboCreateDTO;
import com.example.projectbase.domain.entity.CategoryEntity;
import com.example.projectbase.domain.entity.ComboEntity;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import com.example.projectbase.domain.entity.ProductEntity;
import com.example.projectbase.exception.AlreadyExistsException;
import com.example.projectbase.repository.CategoryRepository;
import com.example.projectbase.repository.ComboRepository;
import com.example.projectbase.service.ComboService;
import com.example.projectbase.util.BindingResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;
@Service
public class ComboServiceImpl implements ComboService {
    @Autowired
    private ComboRepository comboRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ComboConverter comboConverter;
    @Override
    public ResponseEntity<?> createCombo(ComboCreateDTO comboCreateDTO, BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(comboCreateDTO.getCategoryId());
        if(optionalCategoryEntity.isPresent()){
            ComboEntity comboEntity=comboConverter.converDTOToEntity(comboCreateDTO);
            return ResponseEntity.ok(comboConverter.convertEntityToDTO(comboRepository.save(comboEntity)));
        }
        else {
            throw new AlreadyExistsException("Category doesn't exists with id = " + comboCreateDTO.getCategoryId());
        }
    }

    @Override
    public ResponseEntity<?> findOne(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(comboConverter.converListEntityToListDTO(comboRepository.findAll()));
    }

    @Override
    public void deleteCombo(Long id) {

    }


}
