package com.sirkaue.demoparkapi.exception;

public class CodigoUniqueViolationException extends RuntimeException {

    private String recurso;
    private String codigo;

    public String getRecurso() {
        return recurso;
    }

    public String getCodigo() {
        return codigo;
    }

    public CodigoUniqueViolationException(String msg) {
        super(msg);
    }
    public CodigoUniqueViolationException(String recurso, String codigo) {
        this.recurso = recurso;
        this.codigo = codigo;
    }
}
