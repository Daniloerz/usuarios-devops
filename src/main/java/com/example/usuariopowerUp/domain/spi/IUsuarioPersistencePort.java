package com.example.usuariopowerUp.domain.spi;

import com.example.usuariopowerUp.domain.model.UsuarioModel;

public interface IUsuarioPersistencePort {
    UsuarioModel saveUsuario(UsuarioModel usuarioModel);

    UsuarioModel findUserByIdPP(Integer id);

}