package com.example.projectbase.service;

import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.LoginRequestDto;
import com.example.projectbase.domain.dto.request.UserRequestDTO;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface UserService {

  UserDto getUserById(String userId);

  PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request);

  UserDto getCurrentUser(UserPrincipal principal);

  ResponseEntity<?> forgotPassWord(String userName);

  ResponseEntity<?> createNewUser(UserRequestDTO userDTO, BindingResult bindingResult);
}
