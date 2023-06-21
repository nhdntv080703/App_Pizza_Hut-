package com.example.demo.service.impl;

import com.example.demo.converter.ProductConverter;
import com.example.demo.dto.request.ProductDTO;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProductEntity;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import com.example.demo.utils.BindingResultUtils;
import com.example.demo.utils.UploadFileCloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
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
    UploadFileCloudinary uploadFileCloudinary;

    @Override
    public ResponseEntity<?> createProduct(@Valid ProductDTO productDTO,
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
    public ResponseEntity<?> findAll(Integer page, Integer size ){
        if (page == null || page < 0 || size == null || size < 0) {
            return ResponseEntity.ok(productConverter.converListEntityToListDTO(productRepository.findAll()));
        }
        Page<ProductEntity> productEntityPage = productRepository.findAll(PageRequest.of(page, size));
        List<ProductEntity> productList = productEntityPage.getContent();
        return ResponseEntity.ok(productConverter.converListEntityToListDTO(productList));
    }

    @Override
    public ResponseEntity<?> findOne(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if(!productEntity.isPresent()){
            throw new NotFoundException("id là " + id + " không tồn tại");
        }
        return ResponseEntity.ok(productConverter.converEntityToDTO(productEntity.get()));
    }

    @Override
    public ResponseEntity<?> findByName(Integer page, Integer size, String nameProduct) {
        List<ProductEntity> productEntities = productRepository.findByProductName(nameProduct);
        if(productEntities == null || productEntities.isEmpty()){
            throw new NotFoundException("name là " + nameProduct + " không tồn tại");
        }
        if (page == null || page < 0 || size == null || size < 0) {
            return ResponseEntity.ok(productConverter.converListEntityToListDTO(productEntities));
        }
        List<ProductEntity> productEntityList = productRepository.searchByName(nameProduct, PageRequest.of(page, size));
        return ResponseEntity.ok(productConverter.converListEntityToListDTO(productEntityList));

    }

    @Override
    public ResponseEntity<?> updateProduct(Long id, ProductDTO productDTO, BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(id);
        if(optionalProductEntity.isPresent()){
            ProductEntity existPhoneEntity = optionalProductEntity.get();
            Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(existPhoneEntity.getCategory().getId());
            if(optionalCategoryEntity.isPresent()){
                existPhoneEntity = productConverter.converDTOToEntity(productDTO);
                ProductEntity productEntity = productRepository.save(existPhoneEntity);
                return  ResponseEntity.ok(productConverter.converEntityToDTO(productEntity));
            }
            else {
                throw new NotFoundException("Category not found with id = " + existPhoneEntity.getCategory().getId());
            }
        }
        else{
            throw new NotFoundException("Phone not found with id = " + id);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if(!productEntity.isPresent()){
            throw new NotFoundException("không tìm thấy sản phẩm với id là " + id);
        }
        productRepository.deleteById(id);
    }


}
