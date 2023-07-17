package com.example.projectbase.service.impl;

import com.example.projectbase.converter.ProductConverter;
import com.example.projectbase.domain.dto.request.ProductCreateDTO;
import com.example.projectbase.domain.dto.request.ProductSearchPizzaDTO;
import com.example.projectbase.domain.dto.response.ProductResponseDTO;
import com.example.projectbase.domain.entity.CategoryEntity;
import com.example.projectbase.domain.entity.ProductEntity;
import com.example.projectbase.domain.entity.SizeEntity;
import com.example.projectbase.exception.AlreadyExistsException;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.CategoryRepository;
import com.example.projectbase.repository.ProductRepository;
import com.example.projectbase.repository.ProductSizeRepository;
import com.example.projectbase.service.ProductService;
import com.example.projectbase.util.BindingResultUtils;
import com.example.projectbase.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductConverter productConverter;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Override
    public ResponseEntity<?> findOne(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if(!productEntity.isPresent()){
            throw new NotFoundException("id là " + id + " không tồn tại");
        }
        return ResponseEntity.ok(productConverter.converEntityToDTO(productEntity.get()));
    }

    @Override
    public ResponseEntity<?> findWithPizza(ProductSearchPizzaDTO productSearchPizzaDTO){
        List<ProductEntity> productList = productRepository.findByCategoryId(productSearchPizzaDTO.getCategoryId());
        if (productSearchPizzaDTO.getCakeSize() == null && productSearchPizzaDTO.getCakeBase() == null) {
            return ResponseEntity.ok(productConverter.converListEntityToListDTO(productList));
        }
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (ProductEntity item : productList){
            ProductResponseDTO productResponseDTO = productConverter.converEntityToDTO(item);
            if(item.getId() == productSearchPizzaDTO.getId()){
                if(!StringUtils.isNullOrEmpty(productSearchPizzaDTO.getCakeSize())){
                    //get price tu bang product_size len
                    //add vao dto de hien thi len
                    String price = String.valueOf(productSizeRepository.findPriceByProductIdAndSizeId(productSearchPizzaDTO.getId(), productSearchPizzaDTO.getCakeSizeId()));
                    productResponseDTO.setPrice(price);
                    productResponseDTO.setCakeSize(productResponseDTO.getCakeSize());
                }
                if(!StringUtils.isNullOrEmpty(productSearchPizzaDTO.getCakeBase())){
//                    add vao dto de hien thi len
                    productResponseDTO.setCakeBase(productSearchPizzaDTO.getCakeBase());
                }
            }
            //neu khong co thi gan mac dinh
            productResponseDTOS.add(productResponseDTO);
        }
        return ResponseEntity.ok(productConverter.converListEntityToListDTO(productList));
    }

    @Override
    public ResponseEntity<?> createProduct(@Valid ProductCreateDTO productDTO,
                                           BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(productDTO.getCategoryId());
        if(optionalCategoryEntity.isPresent()){
            ProductEntity productEntity = productConverter.converDTOToEntity(productDTO);
            return ResponseEntity.ok(productConverter.converEntityToDTO(productRepository.save(productEntity)));
        }
        else {
            throw new AlreadyExistsException("Category already exists with id = " + productDTO.getCategoryId());
        }
    }

    @Override
    public ResponseEntity<?> updateProduct( ProductCreateDTO productDTO, BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productDTO.getId());
        if(optionalProductEntity.isPresent()){
            ProductEntity existPhoneEntity = optionalProductEntity.get();
            Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(existPhoneEntity.getCategoryEntity().getId());
            if(optionalCategoryEntity.isPresent()){
                existPhoneEntity = productConverter.converDTOToEntity(productDTO);
                ProductEntity productEntity = productRepository.save(existPhoneEntity);
                return  ResponseEntity.ok(productConverter.converEntityToDTO(productEntity));
            }
            else {
                throw new NotFoundException("Category not found with id = " + existPhoneEntity.getCategoryEntity().getId());
            }
        }
        else{
            throw new NotFoundException("Phone not found with id = " + productDTO.getId());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if(!productEntity.isPresent()){
            throw new NotFoundException("không tìm thấy sản phẩm với id là " + id);
        }
        //check khoas ngoaij
        productRepository.deleteById(id);
    }
}
