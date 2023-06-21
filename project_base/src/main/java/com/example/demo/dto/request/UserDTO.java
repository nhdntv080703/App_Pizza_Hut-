package com.example.demo.dto.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class UserDTO {

    @NotBlank(message = "fullName is not empty and null")
    private String fullName;

    @NotBlank(message = "userName is not empty and null")
    private String userName;

    @NotBlank(message = "passWord is not empty and null")
    private String passWord;

    @NotBlank(message = "email is not empty and null")
    private String email;

    private String address;

    private Date birthday;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
