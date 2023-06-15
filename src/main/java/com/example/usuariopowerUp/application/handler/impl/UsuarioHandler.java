package com.example.usuariopowerUp.application.handler.impl;

import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.application.handler.IUsuarioHandler;
import com.example.usuariopowerUp.application.mapper.IUsuarioRequestMapper;
import com.example.usuariopowerUp.domain.api.IUsuarioServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioHandler implements IUsuarioHandler {
    private final IUsuarioServicePort usuarioServicePort;
    private final IUsuarioRequestMapper usuarioRequestMapper;

    @Override
    public void saveCliente(UsuarioRequestDto userRequestDto, String role) {
        usuarioServicePort.saveCliente(usuarioRequestMapper.toObject(userRequestDto), role);
    }

    @Override
    public void savePropietario(Integer idAdmin, UsuarioRequestDto userRequestDto, String role) {
        usuarioServicePort.savePropietario(idAdmin, usuarioRequestMapper.toObject(userRequestDto), role);
    }
    @Override
    public void saveEmpleado(Integer idPropietario, UsuarioRequestDto userRequestDto, String role) {
        usuarioServicePort.saveEmpleado(idPropietario, usuarioRequestMapper.toObject(userRequestDto), role);
    }

    @Override
    public UsuarioResponseDto findUserById(Integer id) {

        return usuarioServicePort.findUserByIdSP(id);
    }


}
