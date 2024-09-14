package com.sirkaue.demoparkapi.exception;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException(String messageKey) {
        super(messageKey);
    }
}
