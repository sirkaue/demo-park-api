package com.sirkaue.demoparkapi.exception;

public class UsuarioNotFoundException extends RuntimeException {

    private final Long codigo;

    public UsuarioNotFoundException(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }
}
