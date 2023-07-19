package com.example.projectbase.controller;

import com.example.projectbase.domain.dto.request.CartCreateDTO;
import com.example.projectbase.domain.dto.request.ComboCreateDTO;
import com.example.projectbase.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CartCreateDTO cartCreateDTO){
        return ResponseEntity.ok(cartService.create(cartCreateDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        cartService.delete(id);
        return ResponseEntity.ok("xoa thanh cong");
    }
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(cartService.findAll());
    }

}
