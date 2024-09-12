package com.sirkaue.demoparkapi.exception;

public class EntityNotFoundException extends RuntimeException {

    private String recurso;
    private String codigo;

    public String getRecurso() {
        return recurso;
    }

    public String getCodigo() {
        return codigo;
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String recurso, String codigo) {
        this.recurso = recurso;
        this.codigo = codigo;
    }
}
