package com.sirkaue.demoparkapi.exception;

public class UsernameUniqueViolationException extends RuntimeException {

    private final String codigo;

    public String getCodigo() {
        return codigo;
    }

    public UsernameUniqueViolationException(String codigo) {
        this.codigo = codigo;
    }
}
