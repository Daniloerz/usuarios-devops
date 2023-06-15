package com.example.usuariopowerUp.infrastructure.input.rest;

import com.example.usuariopowerUp.application.dto.request.UsuarioRequestDto;
import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.application.handler.IUsuarioHandler;
import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.infrastructure.input.enums.RoleEnum;
import com.example.usuariopowerUp.infrastructure.out.jpa.adapter.UsuarioJpaAdapter;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

class UsuarioRestControllerTest {

    private  IUsuarioHandler usuarioHandler = Mockito.mock(IUsuarioHandler.class);

    private UsuarioRestController usuarioRestController = new UsuarioRestController(usuarioHandler);


    static final UsuarioRequestDto usuarioRequestDto = new UsuarioRequestDto();

    @BeforeEach
    void setUp() {
        usuarioRequestDto.setNombre("Danilo");
        usuarioRequestDto.setApellido("Ramirez");
        usuarioRequestDto.setDocumento("1144071226");
        usuarioRequestDto.setCelular("+573165365253");
        usuarioRequestDto.setCorreo("danilo.r@hotmail.com");
        usuarioRequestDto.setClave("1234");
    }

    @Test
    void createOwnerUser() {
        Integer idAdmin = 1;

        Mockito.doNothing().when(usuarioHandler)
                .savePropietario(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any());
        assertDoesNotThrow(() -> usuarioRestController.createOwnerUser(idAdmin,
               usuarioRequestDto));
    }

    @Test
    void createEmployeeUser() {
        Integer idPropietario = 1;

        Mockito.doNothing().when(usuarioHandler)
                .saveEmpleado(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any());
        assertDoesNotThrow(() -> usuarioRestController.createEmployeeUser(idPropietario,
                usuarioRequestDto));
    }

    @Test
    void createClientUser() {
        Mockito.doNothing().when(usuarioHandler)
                .saveCliente(ArgumentMatchers.any(),ArgumentMatchers.any());
        assertDoesNotThrow(() -> usuarioRestController.createClientUser(usuarioRequestDto));
    }

    @Test
    void findUsuarioById() {

        Integer idUsuario = 1;
        UsuarioResponseDto usuarioResponseDto = new UsuarioResponseDto();

        Mockito.when(usuarioHandler.findUserById(anyInt())).thenReturn(usuarioResponseDto);

        ResponseEntity<UsuarioResponseDto> usuarioResponseDto1 = usuarioRestController.findUsuarioById(idUsuario);

        assertEquals(HttpStatus.OK, usuarioResponseDto1.getStatusCode());
        assertEquals(usuarioResponseDto, usuarioResponseDto1.getBody());
    }
}