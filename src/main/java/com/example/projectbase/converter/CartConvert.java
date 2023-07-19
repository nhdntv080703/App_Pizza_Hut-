package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.CartCreateDTO;
import com.example.projectbase.domain.dto.response.CartResponseDTO;
import com.example.projectbase.domain.entity.CartEntity;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import com.example.projectbase.repository.CartRepository;
import com.example.projectbase.repository.ProductDetailRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CartConvert {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;
    public CartEntity converDTOToEntity(CartCreateDTO cartCreateDTO){
        CartEntity cartEntity=modelMapper.map(cartCreateDTO,CartEntity.class);
//        cartEntity.setUserEntity(userRepository.fin);
        cartEntity.setProductDetailEntity(productDetailRepository.findById(cartCreateDTO.getProductDetailId()).get());
        return cartEntity;
    }
    public CartResponseDTO convertEntityToDTO(CartEntity cartEntity){
        CartResponseDTO cartResponseDTO=modelMapper.map(cartEntity,CartResponseDTO.class);
        cartResponseDTO.setUsername(cartEntity.getUserEntity().getUsername());
        cartResponseDTO.setProductName(cartEntity.getProductDetailEntity().getProductEntity().getName());
        return cartResponseDTO;
    }
    public List<CartResponseDTO> convertListEntityToListDTO(List<CartEntity> cartEntities){
        List<CartResponseDTO> cartResponseDTOS=new ArrayList<>();
        for(CartEntity x:cartEntities){
            cartResponseDTOS.add(convertEntityToDTO(x));
        }
        return cartResponseDTOS;
    }
}
