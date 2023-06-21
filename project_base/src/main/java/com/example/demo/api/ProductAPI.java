package com.example.demo.api;

import com.example.demo.dto.request.ProductDTO;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/product")
@RestController
public class ProductAPI {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct (@Valid @ModelAttribute ProductDTO productDTO, BindingResult bindingResult) {
        return productService.createProduct(productDTO, bindingResult);
    }

    @GetMapping("/all_product")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false) Integer page,
                                     @RequestParam(name = "size", required = false) Integer size){
        return productService.findAll(page, size);
    }

    @GetMapping("/one_product")
    public ResponseEntity<?> findOne(@RequestParam Long id){
        return productService.findOne(id);
    }

    @GetMapping("/product_by_name")
    public ResponseEntity<?> findByName(@RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name = "size", required = false) Integer size,
                                        @RequestParam String nameProduct){
        return productService.findByName(page, size, nameProduct);
    }

    @PutMapping
    public ResponseEntity<?> updateProduct (@Valid @RequestParam Long id, @ModelAttribute ProductDTO productDTO, BindingResult bindingResult) {
        return productService.updateProduct(id, productDTO, bindingResult);
    }

    @DeleteMapping
    public void deleteProduct(@RequestParam Long id){
        productService.deleteProduct(id);
    }
}
