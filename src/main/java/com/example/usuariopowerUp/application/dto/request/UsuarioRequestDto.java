package com.example.usuariopowerUp.application.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDto {
    private String nombre;
    private String apellido;
    private String documento;
    private String celular;
    private String correo;
    private String clave;
    @JsonIgnore
    private Integer idRole;
}
