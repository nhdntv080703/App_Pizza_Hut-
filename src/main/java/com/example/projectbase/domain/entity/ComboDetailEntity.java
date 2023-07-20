package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "combo_detail")
public class ComboDetailEntity  extends BaseEntity {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "combo_id")
    private ComboEntity comboEntity;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetailEntity productDetailEntity;
}
