package com.example.usuariopowerUp.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Roles", schema = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    private String nombre;
    private String descripcion;


}
