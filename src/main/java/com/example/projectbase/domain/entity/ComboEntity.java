package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "combo")
public class ComboEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable =  false)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_COMBO_CATEGORY"))
    private CategoryEntity categoryEntity;

    @ManyToMany
    @JoinTable(
            name = "product_combo",
            joinColumns = @JoinColumn(name = "combo_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<ProductEntity> productEntities = new ArrayList<>();
}
