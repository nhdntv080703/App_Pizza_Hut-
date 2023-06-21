package com.example.demo.repository;

import com.example.demo.entity.RoleEntity;
import com.example.demo.enums.EnumRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.List;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findRoleEntityByRoleName(String roleName);
}
