package com.sirkaue.demoparkapi.exception;

public class UsernameNotFoundException extends RuntimeException {

    private final String codigo;

    public UsernameNotFoundException(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
