package com.example.usuariopowerUp.domain.api;


import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.domain.model.UsuarioModel;

public interface IUsuarioServicePort {

    void savePropietario(Integer idAdmin, UsuarioModel usuarioModel, String role);
    void saveCliente(UsuarioModel usuarioModel, String role);
    void saveEmpleado(Integer idPropietario, UsuarioModel usuarioModel, String role);

    UsuarioResponseDto findUserByIdSP(Integer id);
}