package com.sirkaue.demoparkapi.exception;

public class UsernameUniqueViolationException extends RuntimeException {

    private final String recurso;
    private final String codigo;

    public String getRecurso() {
        return recurso;
    }

    public String getCodigo() {
        return codigo;
    }

    public UsernameUniqueViolationException(String codigo) {
        this(null, codigo);
    }

    public UsernameUniqueViolationException(String recurso, String codigo) {
        this.recurso = recurso;
        this.codigo = codigo;
    }
}
