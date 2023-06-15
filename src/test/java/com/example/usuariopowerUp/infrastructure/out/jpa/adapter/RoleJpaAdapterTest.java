package com.example.usuariopowerUp.infrastructure.out.jpa.adapter;

import com.example.usuariopowerUp.domain.model.RoleModel;
import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.infrastructure.out.jpa.entity.RoleEntity;
import com.example.usuariopowerUp.infrastructure.out.jpa.entity.UsuarioEntity;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IUsuarioEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IRoleRepository;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class RoleJpaAdapterTest {

    private IRoleRepository roleRepository = Mockito.mock(IRoleRepository.class);
    private IRoleEntityMapper roleEntityMapper = Mockito.mock(IRoleEntityMapper.class);

    private RoleJpaAdapter roleJpaAdapter = new RoleJpaAdapter(roleRepository, roleEntityMapper);

    static final RoleModel roleModel = new RoleModel();
    static final RoleEntity roleEntity = new RoleEntity();


    @BeforeEach
    void init() {
        roleEntity.setId(1);
        roleEntity.setNombre("Propietario");
        roleEntity.setDescripcion("Rol de propietario");

    }

    @Test
    void findByNombre() {
        String nombre = "Danilo";
        Mockito.when(roleRepository.findByNombre(ArgumentMatchers.any())).thenReturn(roleEntity);
        Mockito.when(roleEntityMapper.toRoleModel(ArgumentMatchers.any(RoleEntity.class))).thenReturn(roleModel);

        assertDoesNotThrow(() -> roleJpaAdapter.findByNombre(nombre));

    }

    @Test
    void findRoleByIdPP() {
        Integer idUsuario = 1;
        Optional<RoleEntity> roleEntityOptional = Optional.of(roleEntity);

        Mockito.when(roleRepository.findById(ArgumentMatchers.any())).thenReturn(roleEntityOptional);
        Mockito.when(roleEntityMapper.toRoleModel(ArgumentMatchers.any(RoleEntity.class))).thenReturn(roleModel);

        assertDoesNotThrow(() -> roleJpaAdapter.findRoleByIdPP(idUsuario));

    }
}