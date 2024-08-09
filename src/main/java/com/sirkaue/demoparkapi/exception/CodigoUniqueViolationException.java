package com.sirkaue.demoparkapi.exception;

public class CodigoUniqueViolationException extends RuntimeException {
    public CodigoUniqueViolationException(String msg) {
        super(msg);
    }
}
