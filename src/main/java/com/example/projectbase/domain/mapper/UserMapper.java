package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.request.UserRequestDTO;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity toUser(UserRequestDTO userCreateDTO);

  @Mappings({
      @Mapping(target = "roleName", source = "userEntity.roleEntity.name"),
  })
  UserDto toUserDto(UserEntity userEntity);

  List<UserDto> toUserDtos(List<UserEntity> userEntity);

}
