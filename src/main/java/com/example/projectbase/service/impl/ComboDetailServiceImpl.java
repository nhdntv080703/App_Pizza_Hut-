package com.example.projectbase.service.impl;

import com.example.projectbase.converter.ComboDetailConvert;
import com.example.projectbase.domain.dto.request.ComboDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ComboDetailResposeDTO;
import com.example.projectbase.domain.entity.ComboDetailEntity;
import com.example.projectbase.repository.ComboDetailRepository;
import com.example.projectbase.service.ComboDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComboDetailServiceImpl implements ComboDetailService {
    @Autowired
   ComboDetailConvert comboDetailConvert;
    @Autowired
    ComboDetailRepository comboDetailRepository;
    @Override
    public ComboDetailResposeDTO create(ComboDetailCreateDTO comboDetailCreateDTO) {
        ComboDetailEntity comboDetailEntity=comboDetailConvert.convertDTOToEntity(comboDetailCreateDTO);
        return comboDetailConvert.convertEntityToDTO(comboDetailRepository.save(comboDetailEntity));
    }
}
