package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.UserCreateDto;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.RoleEntity;
import com.example.projectbase.domain.entity.UserEntity;
import com.example.projectbase.domain.entity.UserEntity.UserEntityBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-16T23:51:05+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_382 (IBM Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUser(UserCreateDto userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.password( userCreateDTO.getPassword() );
        userEntity.username( userCreateDTO.getUsername() );
        userEntity.firstName( userCreateDTO.getFirstName() );
        userEntity.lastName( userCreateDTO.getLastName() );

        return userEntity.build();
    }

    @Override
    public UserDto toUserDto(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setRoleName( userEntityRoleEntityName( userEntity ) );
        userDto.setCreatedDate( userEntity.getCreatedDate() );
        userDto.setLastModifiedDate( userEntity.getLastModifiedDate() );
        userDto.setId( userEntity.getId() );
        userDto.setUsername( userEntity.getUsername() );
        userDto.setFirstName( userEntity.getFirstName() );
        userDto.setLastName( userEntity.getLastName() );

        return userDto;
    }

    @Override
    public List<UserDto> toUserDtos(List<UserEntity> userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( userEntity.size() );
        for ( UserEntity userEntity1 : userEntity ) {
            list.add( toUserDto( userEntity1 ) );
        }

        return list;
    }

    private String userEntityRoleEntityName(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }
        RoleEntity roleEntity = userEntity.getRoleEntity();
        if ( roleEntity == null ) {
            return null;
        }
        String name = roleEntity.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
