package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity,Long> {
}
