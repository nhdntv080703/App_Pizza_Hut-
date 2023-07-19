package com.example.projectbase.domain.dto.request;

import com.example.projectbase.domain.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartCreateDTO {
    private Long id;
    private Integer quantity;
    private Long total_money;
    private Long userId;
    private Long productDetailId;
}
