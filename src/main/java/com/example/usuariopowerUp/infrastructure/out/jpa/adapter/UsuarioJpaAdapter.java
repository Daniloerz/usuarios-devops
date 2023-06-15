package com.example.usuariopowerUp.infrastructure.out.jpa.adapter;


import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.domain.spi.IUsuarioPersistencePort;
import com.example.usuariopowerUp.infrastructure.exception.NoDataFoundException;
import com.example.usuariopowerUp.infrastructure.out.jpa.entity.UsuarioEntity;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IUsuarioEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UsuarioJpaAdapter implements IUsuarioPersistencePort {

    private final IUsuarioRepository usuarioRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;

    @Override
    public UsuarioModel saveUsuario(UsuarioModel usuarioModel) {
        UsuarioEntity usuarioEntity = usuarioRepository.save(usuarioEntityMapper.toEntity(usuarioModel));
        return usuarioEntityMapper.toUsuarioModel(usuarioEntity);
    }

    @Override
    public UsuarioModel findUserByIdPP(Integer id) throws NoDataFoundException {
        Optional<UsuarioEntity> usuarioEntityOptional = usuarioRepository.findById(id);
        if (usuarioEntityOptional.isPresent()){
            UsuarioEntity usuarioEntity = usuarioEntityOptional.get();
            return usuarioEntityMapper.toUsuarioModel(usuarioEntity);
        } else {
            throw new NoDataFoundException("Usuario no encontrado");
        }
    }
}