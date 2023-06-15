package com.example.usuariopowerUp.domain.usecase;

import com.example.usuariopowerUp.application.dto.response.UsuarioResponseDto;
import com.example.usuariopowerUp.domain.exception.ValidationException;
import com.example.usuariopowerUp.domain.model.RoleModel;
import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.domain.spi.IRolePersistencePort;
import com.example.usuariopowerUp.domain.spi.IUsuarioPersistencePort;
import nonapi.io.github.classgraph.utils.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

class UsuarioUseCaseTest {
    private final IUsuarioPersistencePort usuarioPersistencePort = Mockito.mock(IUsuarioPersistencePort.class);
    private final IRolePersistencePort rolePersistencePort = Mockito.mock(IRolePersistencePort.class);
    private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);

    static final UsuarioModel usuarioModel = new UsuarioModel();

    private final UsuarioUseCase usuarioUseCase = new UsuarioUseCase(usuarioPersistencePort,
            rolePersistencePort,
            passwordEncoder);

    @BeforeEach
    void init(){
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirez@hotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");
    }

    @Test
    void saveCliente() {

        RoleModel roleModel = new RoleModel(3, "cliente", "cliente");
        Mockito.when(rolePersistencePort.findByNombre(any())).thenReturn(roleModel);
        Mockito.when(passwordEncoder.encode(any())).thenReturn("*************");
        Mockito.when(usuarioPersistencePort.saveUsuario(any())).thenReturn(usuarioModel);

        assertDoesNotThrow(() -> usuarioUseCase.saveCliente(usuarioModel, "cliente"));

    }

    @Test
    void savePropietario(){

        Integer idAdmin = 4;

        UsuarioModel usuarioAdmin = new UsuarioModel();
        usuarioAdmin.setClave("1234");
        usuarioAdmin.setApellido("Ramirez");
        usuarioAdmin.setCelular("3165365352");
        usuarioAdmin.setCorreo("danilo.ramirez@hotmail.com");
        usuarioAdmin.setDocumento("1144071226");
        usuarioAdmin.setNombre("Danilo");
        usuarioAdmin.setId(1);
        usuarioAdmin.setIdRole(4);


        RoleModel roleModelAdmin = new RoleModel(4, "administrador", "administrador");
        Mockito.when(usuarioPersistencePort.findUserByIdPP(any())).thenReturn(usuarioAdmin);
        Mockito.when(rolePersistencePort.findByNombre(any())).thenReturn(roleModelAdmin);
        Mockito.when(passwordEncoder.encode(any())).thenReturn("*************");
        Mockito.when(usuarioPersistencePort.saveUsuario(any())).thenReturn(usuarioModel);

        assertDoesNotThrow(() -> usuarioUseCase.savePropietario(idAdmin, usuarioModel, "propietario"));
    }

    @Test
    void notSavePropietario(){

        UsuarioModel usuarioAdmin = new UsuarioModel();
        usuarioAdmin.setClave("1234");
        usuarioAdmin.setApellido("Ramirez");
        usuarioAdmin.setCelular("3165365352");
        usuarioAdmin.setCorreo("danilo.ramirez@hotmail.com");
        usuarioAdmin.setDocumento("1144071226");
        usuarioAdmin.setNombre("Danilo");
        usuarioAdmin.setId(1);
        usuarioAdmin.setIdRole(2);


        RoleModel roleModelAdmin = new RoleModel(3, "cliente", "cliente");
        RoleModel roleModel = new RoleModel(3, "cliente", "cliente");
        Mockito.when(usuarioPersistencePort.findUserByIdPP(any())).thenReturn(usuarioAdmin);
        Mockito.when(rolePersistencePort.findByNombre(any())).thenReturn(roleModelAdmin);
        Mockito.when(rolePersistencePort.findByNombre(any())).thenReturn(roleModel);
        Mockito.when(passwordEncoder.encode(any())).thenReturn("*************");
        Mockito.when(usuarioPersistencePort.saveUsuario(any())).thenReturn(usuarioModel);

        ValidationException exception = assertThrows(ValidationException.class,
                () -> usuarioUseCase.savePropietario(any(), usuarioModel, "propietario"));

        assertEquals("Role invalido para crear usuario tipo \"Propietario\"", exception.getMessage());
    }

    @Test
    void saveEmpleado(){

        UsuarioModel usuarioPropietario = new UsuarioModel();
        usuarioPropietario.setClave("1234");
        usuarioPropietario.setApellido("Ramirez");
        usuarioPropietario.setCelular("3165365352");
        usuarioPropietario.setCorreo("danilo.ramirez@hotmail.com");
        usuarioPropietario.setDocumento("1144071226");
        usuarioPropietario.setNombre("Danilo");
        usuarioPropietario.setId(1);
        usuarioPropietario.setIdRole(1);

        RoleModel rolePropietario = new RoleModel(1, "Propietario", "Propietario");
        Mockito.when(usuarioPersistencePort.findUserByIdPP(any())).thenReturn(usuarioPropietario);
        Mockito.when(rolePersistencePort.findByNombre(any())).thenReturn(rolePropietario);
        Mockito.when(passwordEncoder.encode(any())).thenReturn("*************");
        Mockito.when(usuarioPersistencePort.saveUsuario(any())).thenReturn(usuarioModel);

        assertDoesNotThrow(() -> usuarioUseCase.saveEmpleado(any(), usuarioModel, "empleado"));
    }

    @Test
    void saveUsuarioWithInvalidEmail() {
        usuarioModel.setCorreo("danilo.ramirezhotmail.com");

        Mockito.when(usuarioPersistencePort.saveUsuario(any(UsuarioModel.class))).thenReturn(usuarioModel);

        ValidationException exception= assertThrows(ValidationException.class, () ->
                usuarioUseCase.saveCliente(usuarioModel, "propietario"));
        assertEquals("Correo invalido", exception.getMessage());
    }

    @Test
    void saveUsuarioWithInvalidCelular() {
        usuarioModel.setCelular("31653");

        Mockito.when(usuarioPersistencePort.saveUsuario(any())).thenReturn(usuarioModel);

        ValidationException exception= assertThrows(ValidationException.class, () ->
                usuarioUseCase.saveCliente(usuarioModel, "cliente"));
        assertEquals("Celular invalido", exception.getMessage());
    }

    @Test
    void saveUsuarioWithInvalidDocumento() {
        usuarioModel.setDocumento("114407k226");

        Mockito.when(usuarioPersistencePort.saveUsuario(any())).thenReturn(usuarioModel);

        ValidationException exception= assertThrows(ValidationException.class, () ->
                usuarioUseCase.saveCliente(usuarioModel, "cliente"));
        assertEquals("Documento invalido", exception.getMessage());
    }

    @Test
    void findUserByIdSP() {
        Integer id = 1;

        usuarioModel.setId(id);
        usuarioModel.setIdRole(id);

        RoleModel roleModel = new RoleModel(1, "propietario", "propietario");

        Mockito.when(usuarioPersistencePort.findUserByIdPP(anyInt())).thenReturn(usuarioModel);
        Mockito.when(rolePersistencePort.findRoleByIdPP(anyInt())).thenReturn(roleModel);

        UsuarioResponseDto usuarioResponseDto = usuarioUseCase.findUserByIdSP(id);

        assertEquals(usuarioModel.getApellido(), usuarioResponseDto.getApellido());
        assertEquals(usuarioModel.getCelular(), usuarioResponseDto.getCelular());
        assertEquals(usuarioModel.getCorreo(), usuarioResponseDto.getCorreo());
        assertEquals(roleModel.getNombre(), usuarioResponseDto.getRole().getNombre());
    }
}