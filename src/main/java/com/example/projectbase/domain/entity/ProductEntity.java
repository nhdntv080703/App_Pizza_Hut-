package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private CategoryEntity categoryEntity;

    @ManyToMany(mappedBy = "productEntities")
    private List<OrderEntity> orderEntities = new ArrayList<>();

    @ManyToMany(mappedBy = "productEntities")
    private List<ComboEntity> comboEntities = new ArrayList<>();

    @ManyToMany(mappedBy = "productEntityList")
    private List<UserEntity> userEntities = new ArrayList<>();

    @ManyToMany(mappedBy = "productEntityList")
    private List<SizeEntity> sizeEntities = new ArrayList<>();

    @OneToOne(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private ProductPointEntity productPointEntity = new ProductPointEntity();
}
