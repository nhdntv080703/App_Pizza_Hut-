package com.example.demo.converter;

import com.example.demo.dto.request.ProductDTO;
import com.example.demo.dto.response.ProductResponseDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.utils.UploadFileCloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UploadFileCloudinary uploadFileCloudinary;

    public ProductEntity converDTOToEntity(ProductDTO productDTO){
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        try {
            String urlImage = uploadFileCloudinary.getUrlFromFile(productDTO.getImage());
            productEntity.setImage(urlImage);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return productEntity;
    }

    public ProductResponseDTO converEntityToDTO(ProductEntity productEntity){
        ProductResponseDTO productResponseDTO = modelMapper.map(productEntity, ProductResponseDTO.class);
        productResponseDTO.setCategoryId(productEntity.getCategory().getId());
        return productResponseDTO;
    }

    public List<ProductEntity> converListDTOToListEntity(List<ProductDTO> productDTOS){
        List<ProductEntity> productEntities = new ArrayList<>();
        for (ProductDTO item : productDTOS){
            ProductEntity productEntity = modelMapper.map(item, ProductEntity.class);
            productEntities.add(productEntity);
        }
        return productEntities;
    }

    public List<ProductResponseDTO> converListEntityToListDTO(List<ProductEntity> productEntities){
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (ProductEntity item : productEntities){
            ProductResponseDTO productResponseDTO = modelMapper.map(item, ProductResponseDTO.class);
            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }
}
