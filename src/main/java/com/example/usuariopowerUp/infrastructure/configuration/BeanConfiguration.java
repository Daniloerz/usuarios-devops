package com.example.usuariopowerUp.infrastructure.configuration;

import com.example.usuariopowerUp.domain.api.IUsuarioServicePort;
import com.example.usuariopowerUp.domain.spi.IRolePersistencePort;
import com.example.usuariopowerUp.domain.spi.IUsuarioPersistencePort;
import com.example.usuariopowerUp.domain.usecase.UsuarioUseCase;
import com.example.usuariopowerUp.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.example.usuariopowerUp.infrastructure.out.jpa.adapter.UsuarioJpaAdapter;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.mapper.IUsuarioEntityMapper;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IRoleRepository;
import com.example.usuariopowerUp.infrastructure.out.jpa.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUsuarioRepository usuarioRepository;
    private final IRoleRepository roleRepository;
    private final IUsuarioEntityMapper usuarioEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public IUsuarioPersistencePort usuarioPersistencePort() {
        return new UsuarioJpaAdapter(usuarioRepository, usuarioEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort() {
        return new RoleJpaAdapter(roleRepository,
                roleEntityMapper);
    }

    @Bean
    public IUsuarioServicePort usuarioServicePort() {
        return new UsuarioUseCase(usuarioPersistencePort(), rolePersistencePort(), encoder());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}