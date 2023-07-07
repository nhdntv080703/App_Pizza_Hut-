package com.example.projectbase.service.impl;

import com.example.projectbase.constant.ErrorMessage;
import com.example.projectbase.constant.SortByDataConstant;
import com.example.projectbase.converter.UserConverter;
import com.example.projectbase.domain.dto.pagination.PaginationFullRequestDto;
import com.example.projectbase.domain.dto.pagination.PaginationResponseDto;
import com.example.projectbase.domain.dto.request.UserRequestDTO;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.UserEntity;
import com.example.projectbase.domain.mapper.UserMapper;
import com.example.projectbase.email.MailService;
import com.example.projectbase.exception.AlreadyExistsException;
import com.example.projectbase.exception.NotFoundException;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.security.UserPrincipal;
import com.example.projectbase.service.UserService;
import com.example.projectbase.util.BindingResultUtils;
import com.example.projectbase.util.PaginationUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final MailService mailService;

  private final PasswordEncoder passwordEncoder;

  @Autowired
  private UserConverter userConverter;

  @Value("${spring.mail.username}")
  private String gmail;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, MailService mailService, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.mailService = mailService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDto getUserById(String userId) {
    UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.User.ERR_NOT_FOUND_ID, new String[]{userId}));
    return userMapper.toUserDto(userEntity);
  }

  @Override
  public PaginationResponseDto<UserDto> getCustomers(PaginationFullRequestDto request) {
    //Pagination
    Pageable pageable = PaginationUtil.buildPageable(request, SortByDataConstant.USER);
    //Create Output
    return new PaginationResponseDto<>(null, null);
  }

  @Override
  public UserDto getCurrentUser(UserPrincipal principal) {
    UserEntity userEntity = userRepository.getUser(principal);
    return userMapper.toUserDto(userEntity);
  }

  @Override
  public ResponseEntity<?> forgotPassWord(String userName) {
    Optional<UserEntity> user = userRepository.findByUsername(userName);
    if (!user.isPresent()) {
      throw new UsernameNotFoundException(String.format("User with username : %s not found ", userName));
    }
    UserEntity userEntity = user.get();
    String passWord = RandomStringUtils.randomAlphanumeric(5);
    mailService.sendMail(gmail, "Mật khẩu mới của bạn là: " + passWord);
    userEntity.setPassword(passwordEncoder.encode(passWord));
    return ResponseEntity.ok(userConverter.converEntityToDTO(userRepository.save(userEntity)));
  }

  @Override
  public ResponseEntity<?> createNewUser(@Valid UserRequestDTO userDTO,
                                         BindingResult bindingResult) {
    BindingResultUtils.bindResult(bindingResult);
    Optional<UserEntity> userEntity = userRepository.findByUsername(userDTO.getUsername());
    if(!userEntity.isPresent() ){
      UserEntity entityEmail = userRepository.findByEmail(userDTO.getEmail());
      if(entityEmail != null) {
        UserEntity userEntitySave = userConverter.converDTOToEntity(userDTO);
        return ResponseEntity.ok(userConverter.converEntityToDTO(userRepository.save(userEntitySave)));
      }
      throw new AlreadyExistsException("User already exists with username " + userDTO.getEmail());
    }
    else {
      throw new AlreadyExistsException("User already exists with username " + userDTO.getUsername());
    }
  }
}
