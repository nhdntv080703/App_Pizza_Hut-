package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p WHERE p.categoryEntity.id = :categoryId")
    List<ProductEntity> findByCategoryId(Long categoryId);
}
