package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "product_points")
public class ProductPointEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
}
