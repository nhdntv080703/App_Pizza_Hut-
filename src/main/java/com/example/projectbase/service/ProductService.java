package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.ProductCreateDTO;
import com.example.projectbase.domain.dto.request.ProductSearchPizzaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface ProductService {
    ResponseEntity<?> createProduct(@Valid ProductCreateDTO productCreateDTO,
                                    BindingResult bindingResult);
    ResponseEntity<?> findOne(Long id);
    ResponseEntity<?> updateProduct(ProductCreateDTO productDTO, BindingResult bindingResult);
    void deleteProduct(Long id);
    ResponseEntity<?> findWithPizza(ProductSearchPizzaDTO productSearchPizzaDTO);
}
