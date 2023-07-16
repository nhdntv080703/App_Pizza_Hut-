package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.request.ProductCreateDTO;
import com.example.projectbase.domain.dto.request.ProductSearchPizzaDTO;
import com.example.projectbase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

//@RestApiV1
//@RestController("api/v1")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<?> newProduct(@Valid ProductCreateDTO productCreateDTO, BindingResult bindingResult){
        return ResponseEntity.ok(productService.createProduct(productCreateDTO, bindingResult));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@Valid ProductCreateDTO productCreateDTO, BindingResult bindingResult){
        return ResponseEntity.ok(productService.updateProduct(productCreateDTO, bindingResult));
    }

    @GetMapping("/summer-menu")
    public ResponseEntity<?> findSummerMenu(ProductSearchPizzaDTO productSearchPizzaDTO){
        return ResponseEntity.ok(productService.findWithPizza(productSearchPizzaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return null;
    }

    @GetMapping("/pizza")
    public ResponseEntity<?> findPizza(ProductSearchPizzaDTO productSearchPizzaDTO){
        return ResponseEntity.ok(productService.findWithPizza(productSearchPizzaDTO));
    }

    @GetMapping("/pastaAndRice")
    public ResponseEntity<?> findPastaAndRice(){
        return null;
    }

    @GetMapping("/appetizer")
    public ResponseEntity<?> findAppetizer(){
        return null;
    }

    @GetMapping("/drinks")
    public ResponseEntity<?> findDrinks(){
        return null;
    }
}
