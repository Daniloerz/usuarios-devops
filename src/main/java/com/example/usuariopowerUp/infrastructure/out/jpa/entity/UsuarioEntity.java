package com.example.usuariopowerUp.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Usuarios", schema = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nombre;
    private String apellido;
    private String documento;
    private String celular;
    private String correo;
    private String clave;
    @Column(name="id_role")
    private Integer idRole;

}
