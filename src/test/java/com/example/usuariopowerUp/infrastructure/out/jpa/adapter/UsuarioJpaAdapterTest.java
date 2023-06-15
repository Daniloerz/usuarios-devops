package com.example.usuariopowerUp.infrastructure.out.jpa.adapter;

import com.example.usuariopowerUp.domain.model.UsuarioModel;
import com.example.usuariopowerUp.infrastructure.out.jpa.entity.UsuarioEntity;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IUsuarioEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class UsuarioJpaAdapterTest {

    private IUsuarioRepository usuarioRepository = Mockito.mock(IUsuarioRepository.class);
    private IUsuarioEntityMapper usuarioEntityMapper = Mockito.mock(IUsuarioEntityMapper.class);

    private UsuarioJpaAdapter usuarioJpaAdapter = new UsuarioJpaAdapter(usuarioRepository, usuarioEntityMapper);

    static final UsuarioModel usuarioModel = new UsuarioModel();
    static final UsuarioEntity usuarioEntity = new UsuarioEntity();


    @BeforeEach
    void init(){
        usuarioModel.setClave("1234");
        usuarioModel.setApellido("Ramirez");
        usuarioModel.setCelular("3165365352");
        usuarioModel.setCorreo("danilo.ramirez@hotmail.com");
        usuarioModel.setDocumento("1144071226");
        usuarioModel.setNombre("Danilo");

        usuarioEntity.setClave("1234");
        usuarioEntity.setApellido("Ramirez");
        usuarioEntity.setCelular("3165365352");
        usuarioEntity.setCorreo("danilo.ramirezhotmail.com");
        usuarioEntity.setDocumento("1144071226");
        usuarioEntity.setNombre("Danilo");
    }
    @Test
    void saveUsuario() {
        Mockito.when(usuarioEntityMapper.toEntity(ArgumentMatchers.any(UsuarioModel.class))).thenReturn(usuarioEntity);
        Mockito.when(usuarioRepository.save(ArgumentMatchers.any(UsuarioEntity.class))).thenReturn(usuarioEntity);
        Mockito.when(usuarioEntityMapper.toUsuarioModel(ArgumentMatchers.any(UsuarioEntity.class)))
                .thenReturn(usuarioModel);

        UsuarioModel usuarioModelResponse = usuarioJpaAdapter.saveUsuario(usuarioModel);

        assertEquals(usuarioModel.getCorreo(), usuarioModelResponse.getCorreo());
        assertEquals(usuarioModel.getCelular(), usuarioModelResponse.getCelular());
        assertEquals(usuarioModel.getApellido(), usuarioModelResponse.getApellido());
        assertEquals(usuarioModel.getDocumento(), usuarioModelResponse.getDocumento());

    }

    @Test
    void findUserByIdPP() {
        Integer idUsuario = 1;
        Optional<UsuarioEntity> usuarioEntityOptional = Optional.of(usuarioEntity);

        Mockito.when(usuarioRepository.findById(ArgumentMatchers.any())).thenReturn(usuarioEntityOptional);
        Mockito.when(usuarioEntityMapper.toUsuarioModel(ArgumentMatchers.any(UsuarioEntity.class)))
                .thenReturn(usuarioModel);

        assertDoesNotThrow(() -> usuarioJpaAdapter.findUserByIdPP(idUsuario));


    }
}