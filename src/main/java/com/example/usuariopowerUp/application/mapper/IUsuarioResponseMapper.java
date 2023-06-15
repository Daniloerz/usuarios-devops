package com.example.usuariopowerUp.application.mapper;

import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.domain.model.UsuarioModel;

public interface IUsuarioResponseMapper {
    UsuarioResponseDto toResponse(UsuarioModel usuarioModel);

}
