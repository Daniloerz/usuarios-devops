package com.example.usuariopowerUp.infrastructure.out.jpa.adapter;

import com.example.usuariopowerUp.domain.model.RoleModel;
import com.example.usuariopowerUp.domain.spi.IRolePersistencePort;
import com.example.usuariopowerUp.infrastructure.exception.NoDataFoundException;
import com.example.usuariopowerUp.infrastructure.out.jpa.entity.RoleEntity;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;

    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public RoleModel findByNombre(String nombre) {
        return roleEntityMapper.toRoleModel(roleRepository.findByNombre(nombre));
    }

    @Override
    public RoleModel findRoleByIdPP(Integer id) {
        Optional<RoleEntity> roleEntityOptional = roleRepository.findById(id);
        if (roleEntityOptional.isPresent()){
            RoleEntity roleEntity = roleEntityOptional.get();
            return roleEntityMapper.toRoleModel(roleEntity);
        } else {
            throw new NoDataFoundException("Role no encontrado");
        }
    }
}