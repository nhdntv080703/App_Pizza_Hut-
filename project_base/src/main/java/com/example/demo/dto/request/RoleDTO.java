package com.example.demo.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RoleDTO {

    @NotBlank(message = "Name is not empty and null")
    private String name;

    @NotBlank(message = "Name is not empty and null")
    private String code;
}
