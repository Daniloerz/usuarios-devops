package com.example.usuariopowerUp.application.mapper;

import com.example.usuariopowerUp.application.dto.response.RoleResponseDto;
import com.example.usuariopowerUp.domain.model.RoleModel;

public interface IRoleResponseDtoMapper {
    RoleResponseDto toResponse(RoleModel roleModel);

}
