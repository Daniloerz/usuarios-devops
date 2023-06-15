package com.example.usuariopowerUp.application.handler;

import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;

public interface IUsuarioHandler {
    void savePropietario(Integer idAdmin, UsuarioRequestDto userRequestDto, String role);
    void saveCliente(UsuarioRequestDto userRequestDto, String role);
    void saveEmpleado(Integer idPropietario, UsuarioRequestDto userRequestDto, String role);

    UsuarioResponseDto findUserById(Integer id);
}