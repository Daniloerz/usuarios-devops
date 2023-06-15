package com.example.usuariopowerUp.infrastructure.out.jpa.mapper;

import com.example.usuariopowerUp.domain.model.RoleModel;
import com.example.usuariopowerUp.infrastructure.out.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRoleEntityMapper {

    RoleEntity toEntity(RoleModel user);
    RoleModel toRoleModel(RoleEntity roleEntity);

}