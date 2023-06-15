package com.example.usuariopowerUp.infrastructure.input.enums;

import java.util.Arrays;

public enum RoleEnum {
    ADMINISTRADOR ("administrador"),
    PROPIETARIO("propietario"),
    EMPLEADO("empleado"),
    CLIENTE("cliente");

    private String dbName;

    private RoleEnum(String dbName) {
        this.dbName = dbName;
    }

    public String getDbName() {
        return dbName;
    }

    public boolean isValidRole ( String role){
        return Arrays.asList(RoleEnum.values()).stream()
                .anyMatch(roleEnum -> roleEnum.getDbName().equalsIgnoreCase(role));
    }
}
