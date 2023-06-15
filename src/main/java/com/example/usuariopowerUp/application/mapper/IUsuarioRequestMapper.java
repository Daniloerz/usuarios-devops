package com.example.usuariopowerUp.application.mapper;

import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.domain.model.UsuarioModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUsuarioRequestMapper {
    UsuarioModel toObject(UsuarioRequestDto usuarioRequestDto);
}
