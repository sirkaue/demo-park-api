package com.sirkaue.demoparkapi.exception;

public class ClienteNotFoundException extends RuntimeException {

    private final Long codigo;

    public Long getCodigo() {
        return codigo;
    }

    public ClienteNotFoundException(Long codigo) {
        this.codigo = codigo;
    }
}
