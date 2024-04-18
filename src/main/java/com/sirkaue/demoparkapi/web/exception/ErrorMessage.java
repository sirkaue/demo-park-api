package com.sirkaue.demoparkapi.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {
    private String path;
    private String method;
    private int status;
    private String statusText;
    private String message;
    private Map<String, String> errors;

    public ErrorMessage() {
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        path = request.getRequestURI();
        method = request.getMethod();
        this.status = status.value();
        statusText = status.getReasonPhrase();
        this.message = message;
    }
    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult result) {
        path = request.getRequestURI();
        method = request.getMethod();
        this.status = status.value();
        statusText = status.getReasonPhrase();
        this.message = message;
        addErrors(result);
    }

    private void addErrors(BindingResult result) {
        errors = new HashMap<>();
        for (FieldError fieldError : result.getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", status=" + status +
                ", statusText='" + statusText + '\'' +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
