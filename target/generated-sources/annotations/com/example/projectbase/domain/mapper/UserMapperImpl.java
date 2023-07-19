package com.example.projectbase.domain.mapper;

import com.example.projectbase.domain.dto.request.UserCreateDTO;
import com.example.projectbase.domain.dto.response.UserDto;
import com.example.projectbase.domain.entity.RoleEntity;
import com.example.projectbase.domain.entity.UserEntity;
import com.example.projectbase.domain.entity.UserEntity.UserEntityBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-18T22:29:48+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_382 (IBM Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUser(UserCreateDTO userCreateDTO) {
        if ( userCreateDTO == null ) {
            return null;
        }

        UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.password( userCreateDTO.getPassword() );
        userEntity.email( userCreateDTO.getEmail() );
        userEntity.phoneNumber( userCreateDTO.getPhoneNumber() );
        userEntity.address( userCreateDTO.getAddress() );
        userEntity.gender( userCreateDTO.getGender() );
        userEntity.username( userCreateDTO.getUsername() );
        try {
            if ( userCreateDTO.getBirthday() != null ) {
                userEntity.birthday( new SimpleDateFormat().parse( userCreateDTO.getBirthday() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        userEntity.fullName( userCreateDTO.getFullName() );

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
        userDto.setFullName( userEntity.getFullName() );

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
