package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.ProductSizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductSizeRepository extends JpaRepository<ProductSizeEntity, Long> {

    @Query("SELECT p.price FROM ProductSizeEntity p WHERE p.productEntity.id = ?1 AND p.sizeEntity.id = ?2")
    Long findPriceByProductIdAndSizeId(Long productId,Long sizeId);
}
