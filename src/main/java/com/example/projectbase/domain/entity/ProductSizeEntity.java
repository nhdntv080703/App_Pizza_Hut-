package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "product_size")
public class ProductSizeEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private SizeEntity sizeEntity;

    @Column(nullable = false)
    private Long price;

    @OneToMany(mappedBy = "productSizeEntity", cascade = CascadeType.ALL )
    @JsonIgnore
    private Set<CakeBaseEntity> cakeBaseEntitySet = new HashSet<>();
}
