package com.example.demo.service.impl;

import com.example.demo.converter.UserConverter;
import com.example.demo.dto.request.LoginRequest;
import com.example.demo.dto.request.UserDTO;
import com.example.demo.dto.response.UserResponseDTO;
import com.example.demo.email.MailService;
import com.example.demo.entity.UserEntity;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NotFoundException;
import com.example.demo.jwt.JwtUtils;
import com.example.demo.repository.UserRepositoty;
import com.example.demo.service.UserService;
import com.example.demo.utils.BindingResultUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepositoty userRepository;
    @Autowired
    UserConverter userConverter;
    @Autowired
    MailService mailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailServiceImp userDetailServiceImp;

    @Value("${jwt.accessExpirationMs}")
    private Long accessTokenMs;
    @Value("${jwt.expirationMs}")
    private Long freshTokenMs;
    @Value("${gmail}")
    private String gmail;
    @Autowired
    private JwtUtils jwtUtils;
    @Override
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),
                        loginRequest.getPassWord()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailImp userDetailImp = (UserDetailImp) authentication.getPrincipal();
        try {
            String accessToken = jwtUtils.generateTokenByUsername(userDetailImp.getUsername(), 60000L);
            String refreshToken = jwtUtils.generateTokenByUsername(userDetailImp.getUsername(), 6000000L);
            return ResponseEntity.ok(new UserResponseDTO(
                    userDetailImp.getId(),
                    userDetailImp.getFullName(),
                    accessToken,
                    refreshToken,
                    userDetailImp.getAuthorities()
            ));
        } catch (Exception e) {
            return ResponseEntity.ok("Login failed : " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> createNewUser(@Valid UserDTO userDTO,
                                            BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        UserEntity userEntity = userRepository.findByUserName(userDTO.getUserName());
        if(userEntity == null ){
            UserEntity userEntitySave = userConverter.converDTOToEntity(userDTO, userEntity);
            return ResponseEntity.ok(userConverter.converEntityToDTO(userRepository.save(userEntitySave)));
        }
        else {
            throw new AlreadyExistsException("User already exists with username " + userDTO.getUserName());
        }
    }

    @Override
    public ResponseEntity<?> findAll(Integer page, Integer size ){
        if (page == null || page < 0 || size == null || size < 0) {
            return ResponseEntity.ok(userConverter.converListEntityToListDTO(userRepository.findAll()));
        }
        Page<UserEntity> userEntities = userRepository.findAll(PageRequest.of(page, size));
        List<UserEntity> userEntityList = userEntities.getContent();
        return ResponseEntity.ok(userConverter.converListEntityToListDTO(userEntityList));
    }

    @Override
    public ResponseEntity<?> findOne(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()){
            throw new NotFoundException("id là " + id + " không tồn tại");
        }
        return ResponseEntity.ok(userConverter.converEntityToDTO(userEntity.get()));
    }

    @Override
    public ResponseEntity<?> findByName(Integer page, Integer size, String fullName) {
        List<UserEntity> userEntities = userRepository.findByFullName(fullName);
        if(userEntities == null || userEntities.isEmpty()){
            throw new NotFoundException("Full name là " + fullName + " không tồn tại");
        }
        if (page == null || page < 0 || size == null || size < 0) {
            return ResponseEntity.ok(userConverter.converListEntityToListDTO(userEntities));
        }
        List<UserEntity> userEntityList = userRepository.searchByName(fullName, PageRequest.of(page, size));
        return ResponseEntity.ok(userConverter.converListEntityToListDTO(userEntityList));

    }

    @Override
    public ResponseEntity<?> updateUser(UserDTO userDTO, BindingResult bindingResult) {
        BindingResultUtils.bindResult(bindingResult);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailImp userDetailImp = new UserDetailImp();
        if (authentication != null && authentication.isAuthenticated()) {
            String userName = authentication.getPrincipal().toString();
             userDetailImp = (UserDetailImp)userDetailServiceImp.loadUserByUsername(userName);
        }
//        userDetailImp.getFullName();
//        Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
        if(userDetailImp != null){
            UserEntity existUserEntity = userConverter.converUserDetailToEntity(userDetailImp);
            existUserEntity = userConverter.converDTOToEntity(userDTO, existUserEntity);
//            existUserEntity = userConverter.converDTOToEntity(userDTO);
//            existUserEntity.setId(id);
            UserEntity userEntity = userRepository.save(existUserEntity);
            return  ResponseEntity.ok(userConverter.converEntityToDTO(userEntity));
        }
        else{
            throw new NotFoundException("User not found ");
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()){
            throw new NotFoundException("không tìm thấy sản phẩm với id là " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<?> forgotPassWord(String userName) {
        UserEntity user = userRepository.findByUserName(userName);
        if (ObjectUtils.isEmpty(user)) {
            throw new UsernameNotFoundException(String.format("User with username : %s not found ", userName));
        }
        String passWord = RandomStringUtils.randomAlphanumeric(5);
        mailService.sendMail(gmail, "Mật khẩu mới của bạn là: " + passWord);
        user.setPassWord(passwordEncoder.encode(passWord));
        return ResponseEntity.ok(userConverter.converEntityToDTO(userRepository.save(user)));
    }
}
