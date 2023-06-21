package com.example.demo.api;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.ProductDTO;
import com.example.demo.dto.request.UserDTO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserAPI {

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return  userService.login(loginRequest);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassWord(@RequestParam String userName){
        return userService.forgotPassWord(userName);
    }

    @PostMapping("/new-user")
    public ResponseEntity<?> createUser (@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        return userService.createNewUser(userDTO, bindingResult);
    }

    @GetMapping("/all_product")
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false) Integer page,
                                     @RequestParam(name = "size", required = false) Integer size){
        return userService.findAll(page, size);
    }

    @GetMapping("/one_product")
    public ResponseEntity<?> findOne(@RequestParam Long id){
        return userService.findOne(id);
    }

    @GetMapping("/product_by_name")
    public ResponseEntity<?> findByName(@RequestParam(name = "page", required = false) Integer page,
                                        @RequestParam(name = "size", required = false) Integer size,
                                        @RequestParam String nameProduct){
        return userService.findByName(page, size, nameProduct);
    }

    @PutMapping
    public ResponseEntity<?> updateUser (@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult) {
        return userService.updateUser(userDTO, bindingResult);
    }

    @DeleteMapping
    public void deleteUser(@RequestParam Long id){
        userService.deleteUser(id);
    }

}
