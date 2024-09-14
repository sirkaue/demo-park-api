package com.sirkaue.demoparkapi.exception;

public class ClienteCpfNotFoundException extends RuntimeException {

    private final String recurso;
    private final String codigo;

    public ClienteCpfNotFoundException(String recurso, String codigo) {
        this.recurso = recurso;
        this.codigo = codigo;
    }

    public String getRecurso() {
        return recurso;
    }

    public String getCodigo() {
        return codigo;
    }

}
