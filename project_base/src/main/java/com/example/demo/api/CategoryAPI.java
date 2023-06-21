package com.example.demo.api;

import com.example.demo.dto.request.CategoryDTO;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryAPI {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory (@Valid @ModelAttribute CategoryDTO categoryDTO, BindingResult bindingResult) {
        return categoryService.createCategory(categoryDTO, bindingResult);
    }

    @GetMapping("/all_category")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false) Integer page,
                                     @RequestParam(name = "size", required = false) Integer size){
        return categoryService.findAll(page, size);
    }

    @GetMapping("/one_category")
    public ResponseEntity<?> findOne(@RequestParam Long id){
        return categoryService.findOne(id);
    }

    @GetMapping("/category_by_name")
    public ResponseEntity<?> findByName(@RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name = "size", required = false) Integer size,
                                        @RequestParam String nameProduct){
        return categoryService.findByName(page, size, nameProduct);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory (@Valid @RequestParam Long id, @ModelAttribute CategoryDTO categoryDTO, BindingResult bindingResult) {
        return categoryService.updateCategory(id, categoryDTO, bindingResult);
    }

    @DeleteMapping
    public void deleteCategory(@RequestParam Long id){
        categoryService.deleteCategory(id);
    }
}
