package com.example.usuariopowerUp.infrastructure.out.jpa.mapper;

import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.infrastructure.out.jpa.entity.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUsuarioEntityMapper {

    UsuarioEntity toEntity(UsuarioModel user);
    UsuarioModel toUsuarioModel(UsuarioEntity usuarioEntity);

}