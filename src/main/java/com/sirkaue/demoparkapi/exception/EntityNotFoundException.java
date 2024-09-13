package com.sirkaue.demoparkapi.exception;

public class EntityNotFoundException extends RuntimeException {

    private final String codigo;

    public String getCodigo() {
        return codigo;
    }

    public EntityNotFoundException(String codigo) {
        this.codigo = codigo;
    }
}
