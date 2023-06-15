package com.example.usuariopowerUp.infrastructure.out.jpa.repository;

import com.example.usuariopowerUp.infrastructure.out.jpa.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

}