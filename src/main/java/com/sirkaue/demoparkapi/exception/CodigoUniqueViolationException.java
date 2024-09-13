package com.sirkaue.demoparkapi.exception;

public class CodigoUniqueViolationException extends RuntimeException {

    private final String codigo;

    public String getCodigo() {
        return codigo;
    }

    public CodigoUniqueViolationException(String codigo) {
        this.codigo = codigo;
    }
}
