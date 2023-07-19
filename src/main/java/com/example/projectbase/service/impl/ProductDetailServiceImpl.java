package com.example.projectbase.service.impl;

import com.example.projectbase.converter.ProductDetailConverter;
import com.example.projectbase.domain.dto.request.ProductDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ProductDetailResponseDTO;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import com.example.projectbase.repository.ProductDetailRepository;
import com.example.projectbase.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductDetailConverter productDetailConverter;
    @Override
    public ProductDetailResponseDTO create(ProductDetailCreateDTO productDetailCreateDTO) {
        ProductDetailEntity productDetailEntity=productDetailConverter.convertDTOToEntity(productDetailCreateDTO);
        return productDetailConverter.convertEntityToDTO(productDetailRepository.save(productDetailEntity));
    }

    @Override
    public List<ProductDetailResponseDTO> findAll() {
        return productDetailConverter.convertListEntityToListDTO(productDetailRepository.findAll());
    }
}
