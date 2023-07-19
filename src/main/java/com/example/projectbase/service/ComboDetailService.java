package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.ComboDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ComboDetailResposeDTO;

public interface ComboDetailService {
    public ComboDetailResposeDTO create(ComboDetailCreateDTO comboDetailCreateDTO);
}
