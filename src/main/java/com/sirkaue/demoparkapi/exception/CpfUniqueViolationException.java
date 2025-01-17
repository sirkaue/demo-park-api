package com.sirkaue.demoparkapi.exception;

public class CpfUniqueViolationException extends RuntimeException {

    private final String recurso;
    private final String codigo;

    public String getRecurso() {
        return recurso;
    }

    public String getCodigo() {
        return codigo;
    }

    public CpfUniqueViolationException(String recurso, String codigo) {
        this.recurso = recurso;
        this.codigo = codigo;
    }
}
