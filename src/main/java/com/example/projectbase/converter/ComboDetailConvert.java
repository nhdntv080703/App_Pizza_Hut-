package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.ComboDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ComboDetailResposeDTO;
import com.example.projectbase.domain.entity.ComboDetailEntity;
import com.example.projectbase.domain.entity.ComboEntity;
import com.example.projectbase.repository.ComboRepository;
import com.example.projectbase.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComboDetailConvert {
    @Autowired
    private ComboRepository comboRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    public ComboDetailEntity convertDTOToEntity(ComboDetailCreateDTO comboDetailCreateDTO){
        ComboDetailEntity comboDetailEntity=new ComboDetailEntity();
        comboDetailEntity.setComboEntity(comboRepository.findById(comboDetailCreateDTO.getCombo_id()).get());
        comboDetailEntity.setProductDetailEntity(productDetailRepository.findById(comboDetailCreateDTO.getProduct_detail_id()).get());
        return comboDetailEntity;
    }
    public ComboDetailResposeDTO convertEntityToDTO(ComboDetailEntity comboDetailEntity){
        ComboDetailResposeDTO comboDetailResposeDTO=new ComboDetailResposeDTO();
        comboDetailResposeDTO.setComboName(comboDetailEntity.getComboEntity().getName());
        comboDetailResposeDTO.setProductName(comboDetailEntity.getProductDetailEntity().getProductEntity().getName());
        return comboDetailResposeDTO;
    }
}
