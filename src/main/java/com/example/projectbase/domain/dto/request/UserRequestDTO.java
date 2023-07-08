package com.example.projectbase.domain.dto.request;

import lombok.Setter;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
public class UserRequestDTO {

    @NotBlank(message = "fullName is not empty and null")
    private String fullName;

    @NotBlank(message = "username is not empty and null")
    private String username;

    @Valid
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@])[0-9a-zA-Z@]{8,16}$", message = "Mật khẩu không hợp lệ")
    private String password;

    @Valid
    @Pattern(regexp = "\\d{10}", message = "Số điện thoại không hợp lệ")
    private String phoneNumber;

    @Valid
    @Pattern(regexp = "^[\\w.%+-]+@gmail\\.com$", message = "Email phải chứa '@gmail.com'")
    private String email;

    private String gender;

    @NotBlank(message = "address is not empty and null")
    private String address;

    private Date birthday;
}
