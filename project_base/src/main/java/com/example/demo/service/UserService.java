package com.example.demo.service;

import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

public interface UserService {
    ResponseEntity<?> login(LoginRequest loginRequest);
    ResponseEntity<?> createNewUser(@Valid UserDTO userDTO, BindingResult bindingResult);
    ResponseEntity<?> findAll(Integer page, Integer size );
    ResponseEntity<?> findOne(Long id);
    ResponseEntity<?> findByName(Integer page, Integer size, String nameProduct);
    ResponseEntity<?> updateUser( UserDTO userDTO, BindingResult bindingResult);
    void deleteUser(Long id);
    ResponseEntity<?> forgotPassWord(String userName);
}
