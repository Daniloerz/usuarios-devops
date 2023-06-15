package com.example.usuariopowerUp.domain.spi;

import com.example.usuariopowerUp.domain.model.RoleModel;

public interface IRolePersistencePort {
    RoleModel findByNombre(String nombre);

    RoleModel findRoleByIdPP(Integer id);

}