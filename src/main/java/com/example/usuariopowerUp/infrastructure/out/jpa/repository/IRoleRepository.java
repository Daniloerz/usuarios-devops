package com.example.usuariopowerUp.infrastructure.out.jpa.repository;

import com.example.usuariopowerUp.infrastructure.out.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer> {
RoleEntity findByNombre(String nombre);
}