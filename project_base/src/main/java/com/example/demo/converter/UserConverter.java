package com.example.demo.converter;

import com.example.demo.dto.request.UserDTO;
import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepositoty;
import com.example.demo.service.impl.UserDetailImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    hàm conver đang sai, cần sửa
    public UserEntity converDTOToEntity(UserDTO userDTO, UserEntity userEntity){
        Long id = userEntity.getId();
        userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
        RoleEntity role = roleRepository.findRoleEntityByRoleName("ROLE_USER");
        List<RoleEntity> roleEntities = new ArrayList<>();
        roleEntities.add(role);
        userEntity.setRoles(roleEntities);
        userEntity.setId(id);
        return userEntity;
    }

    public UserDTO converEntityToDTO(UserEntity userEntity){
        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);
        return userDTO;
    }

    public Object converListEntityToListDTO(List<UserEntity> userEntityList) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (UserEntity item : userEntityList){
            UserDTO userDTO = modelMapper.map(item, UserDTO.class);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    public UserEntity converUserDetailToEntity(UserDetailImp userDetailImp) {
        UserEntity userEntity = modelMapper.map(userDetailImp, UserEntity.class);
        //set role vào cho userEntity
        List<GrantedAuthority> roles = (List<GrantedAuthority>) userDetailImp.getAuthorities();
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (GrantedAuthority item : roles){
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRoleName(item.getAuthority());
            roleEntities.add(roleEntity);
        }
        userEntity.setRoles(roleEntities);
        return userEntity;
    }
}
