package com.example.projectbase.service.impl;

import com.example.projectbase.domain.dto.CartDto;
import com.example.projectbase.domain.dto.common.UserDetailImp;
import com.example.projectbase.domain.entity.CartEntity;
import com.example.projectbase.domain.entity.ProductEntity;
import com.example.projectbase.domain.entity.UserEntity;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.CartRepository;
import com.example.projectbase.repository.ProductRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomUserDetailsServiceImpl userDetailServiceImp;


    @Override
    public CartEntity create(CartDto cartDto) {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImp userDetailImp = new UserDetailImp();
        if (authentication != null && authentication.isAuthenticated()) {
            String userName = authentication.getPrincipal().toString();
            userDetailImp = (UserDetailImp)userDetailServiceImp.loadUserByUsername(userName);
        }
        else{
            throw new NullPointerException("Loggin request!");
        }
        UserEntity userEntity=userRepository.findByUsername(userDetailImp.getUsername()).orElseThrow(() -> new NotFoundException("Not Found!"));
        ProductEntity productEntity=productRepository.findById(cartDto.getProduct_id()).orElseThrow(() -> new NotFoundException("Not Found"));
        return cartRepository.save(new CartEntity(userEntity,productEntity,cartDto.getQuantity()));
    }

    @Override
    public void deleteById(Long id) {
        if(cartRepository.findById(id).isPresent()){
            cartRepository.deleteById(id);
        }
        else{
            throw new NotFoundException("Not Found!");
        }
    }

    @Override
    public List<CartEntity> findAll() {
        return cartRepository.findAll();
    }
}
