package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.ProductDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ProductDetailResponseDTO;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import com.example.projectbase.domain.entity.ProductEntity;
import com.example.projectbase.repository.CakeBaseRepository;
import com.example.projectbase.repository.ProductRepository;
import com.example.projectbase.repository.SizeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductDetailConverter {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    CakeBaseRepository cakeBaseRepository;
    public ProductDetailEntity convertDTOToEntity(ProductDetailCreateDTO productDetailCreateDTO){
        ProductDetailEntity productDetailEntity=new ProductDetailEntity();
        productDetailEntity.setProductEntity(productRepository.findById(productDetailCreateDTO.getProductId()).orElseThrow(() -> new NullPointerException("khong tim thay product")));
        productDetailEntity.setSizeEntity(sizeRepository.findById(productDetailCreateDTO.getSizeId()).orElseThrow(() -> new NullPointerException("khong tim thay size")));
        productDetailEntity.setCakeBaseEntity(cakeBaseRepository.findById(productDetailCreateDTO.getCakeBaseId()).orElseThrow(() -> new NullPointerException("khong tim thay cakebase")));
        productDetailEntity.setPrice(1L);
        return productDetailEntity;
    }
    public ProductDetailResponseDTO convertEntityToDTO(ProductDetailEntity productDetailEntity){
        ProductDetailResponseDTO productDetailResponseDTO=new ProductDetailResponseDTO();
        productDetailResponseDTO.setProductName(productDetailEntity.getProductEntity().getName());
        productDetailResponseDTO.setCakeBaseName(productDetailEntity.getCakeBaseEntity().getName());
        productDetailResponseDTO.setSizeName(productDetailEntity.getSizeEntity().getName());
        return productDetailResponseDTO;
    }
    public List<ProductDetailResponseDTO> convertListEntityToListDTO(List<ProductDetailEntity> productDetailEntities){
        List<ProductDetailResponseDTO> productDetailResponseDTOS=new ArrayList<>();
        for(ProductDetailEntity x:productDetailEntities){
            productDetailResponseDTOS.add(convertEntityToDTO(x));
        }
        return productDetailResponseDTOS;
    }
}
