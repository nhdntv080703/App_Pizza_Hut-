package com.example.projectbase.domain.dto.common;

import com.example.projectbase.domain.entity.RoleEntity;
import com.example.projectbase.domain.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


public class UserDetailImp implements UserDetails {

    private String id;
    private String fullName;
    private String userName;
    private String passWord;
    private String email;
    private String address;
    private Date birthday;

    public UserDetailImp(String id, String fullName, String username, String password, String email, String address, Date birthday, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.fullName = fullName;
        this.userName = username;
        this.passWord = password;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.authorities = authorities;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public void setPassword(String password) {
        this.passWord = password;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    Collection<? extends GrantedAuthority> authorities;

    public static UserDetailImp map(UserEntity user) {

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRoleEntity().getName()));
        return new UserDetailImp(
                user.getId(),
                user.getFullName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getAddress(),
                user.getBirthday(),
                roles);
    }


    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UserDetailImp() {
    }
}
