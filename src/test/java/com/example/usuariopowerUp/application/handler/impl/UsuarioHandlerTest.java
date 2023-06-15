package com.example.usuariopowerUp.application.handler.impl;

import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.application.handler.IUsuarioHandler;
import com.example.usuariopowerUp.application.mapper.IUsuarioRequestMapper;
import com.example.usuariopowerUp.domain.api.IUsuarioServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class UsuarioHandlerTest {

    private IUsuarioServicePort usuarioServicePort = Mockito.mock(IUsuarioServicePort.class);

    private IUsuarioRequestMapper usuarioRequestMapper = Mockito.mock(IUsuarioRequestMapper.class);

    private UsuarioHandler usuarioHandler = new UsuarioHandler(usuarioServicePort, usuarioRequestMapper);

    @BeforeEach
    void setUp() {
    }

    @Test
    void testSaveCliente() {
        UsuarioRequestDto userRequestDto = new UsuarioRequestDto();
        String role = "cliente";

        Mockito.doNothing().when(usuarioServicePort).saveCliente(any(), anyString());

        usuarioHandler.saveCliente(userRequestDto, role);

        Mockito.verify(usuarioServicePort, Mockito.times(1)).saveCliente(any(), anyString());
    }

    @Test
    void testSavePropietario() {
        Integer idAdmin = 1;
        UsuarioRequestDto userRequestDto = new UsuarioRequestDto();
        String role = "propietario";

        Mockito.doNothing().when(usuarioServicePort).savePropietario(anyInt(), any(), anyString());
        usuarioHandler.savePropietario(idAdmin, userRequestDto, role);

        Mockito.verify(usuarioServicePort, Mockito.times(1))
                .savePropietario(anyInt(), any(), anyString());
    }

    @Test
    void testSaveEmpleado() {
        Integer idPropietario = 1;
        UsuarioRequestDto userRequestDto = new UsuarioRequestDto();
        String role = "empleado";

        Mockito.doNothing().when(usuarioServicePort).saveEmpleado(anyInt(), any(), anyString());

        usuarioHandler.saveEmpleado(idPropietario, userRequestDto, role);

        Mockito.verify(usuarioServicePort, Mockito.times(1)).saveEmpleado(anyInt(), any(), anyString());
    }

    @Test
    void testFindUserById() {
        Integer id = 1;
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();
        Mockito.when(usuarioServicePort.findUserByIdSP(anyInt())).thenReturn(usuarioResponseDto);

        UsuarioResponseDto result = usuarioHandler.findUserById(id);

        assertEquals(usuarioResponseDto, result);

        Mockito.verify(usuarioServicePort, Mockito.times(1)).findUserByIdSP(anyInt());
    }
}