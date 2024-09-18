package com.sirkaue.demoparkapi.exception;

public class VagaNotFoundException extends RuntimeException {

    private final String codigo;

    public String getCodigo() {
        return codigo;
    }

    public VagaNotFoundException(String codigo) {
        this.codigo = codigo;
    }
}
