package com.example.demo.repository;

import com.example.demo.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoty extends JpaRepository<UserEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.productName LIKE %?1%")
    List<UserEntity> searchByName(String name, Pageable pageable);

    @Query("SELECT p FROM UserEntity p WHERE p.userName LIKE %?1%")
    UserEntity findByUserName(String username);

    @Query("SELECT p FROM UserEntity p WHERE p.fullName LIKE %?1%")
    List<UserEntity> findByFullName(String fullName);

}