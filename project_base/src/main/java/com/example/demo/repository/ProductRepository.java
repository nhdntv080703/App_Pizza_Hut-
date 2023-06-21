package com.example.demo.repository;

import com.example.demo.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.productName LIKE %?1%")
    List<ProductEntity> searchByName(String name, Pageable pageable);

    @Query("SELECT p FROM ProductEntity p WHERE p.productName LIKE %?1%")
    List<ProductEntity> findByProductName(String name);

}
