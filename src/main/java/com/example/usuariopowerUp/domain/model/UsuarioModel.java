package com.example.usuariopowerUp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioModel {
    private Integer id;
    private String nombre;
    private String apellido;
    private String documento;
    private String celular;
    private String correo;
    private String clave;
    private Integer idRole;
}
