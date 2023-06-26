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
@Table(name = "cakebase")
public class CakeBaseEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_size_id", foreignKey = @ForeignKey(name = "FK_PRODUCT-SIZE_CAKE-BASE"))
    private ProductSizeEntity productSizeEntity;

}
