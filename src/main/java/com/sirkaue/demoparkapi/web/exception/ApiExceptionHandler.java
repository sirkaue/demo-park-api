package com.sirkaue.demoparkapi.web.exception;

import com.sirkaue.demoparkapi.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    private final MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException ex, HttpServletRequest request) {
        Object[] params = new Object[]{ex.getCodigo()};
        String message = messageSource.getMessage("exception.entityNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<ErrorMessage> usuarioNotFoundException(UsuarioNotFoundException ex, HttpServletRequest request) {
        Object[] params = new Object[]{ex.getCodigo()};
        String message = messageSource.getMessage("exception.usuarioNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> usernameNotFoundException(UsernameNotFoundException ex, HttpServletRequest request) {
        Object[] params = new Object[]{ex.getCodigo()};
        String message = messageSource.getMessage("exception.usuarioUsernameNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(ReciboNotFoundException.class)
    public ResponseEntity<ErrorMessage> reciboEntityNotFoundException(ReciboNotFoundException ex, HttpServletRequest request) {
        Object[] params = new Object[]{ex.getCodigo()};
        String message = messageSource.getMessage("exception.reciboEntityNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(CodigoUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> codigoUniqueViolationException(CodigoUniqueViolationException ex, HttpServletRequest request) {
        Object[] params = new Object[]{ex.getCodigo()};
        String message = messageSource.getMessage("exception.codigoUniqueViolationException",
                params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, message));
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> passwordInvalidException(PasswordInvalidException ex, HttpServletRequest request) {
        String messageKey = ex.getMessage();
        String message = messageSource.getMessage(messageKey, null, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, message));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> accessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.FORBIDDEN, ex.getMessage()));
    }

    @ExceptionHandler(VagaDisponivelException.class)
    public ResponseEntity<ErrorMessage> vagaDisponivelException(RuntimeException ex, HttpServletRequest request) {
        String message = messageSource.getMessage("exception.vagaDisponivelException", null, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorMessage> clienteNotFoundException(ClienteNotFoundException ex, HttpServletRequest request) {
        Object[] params = new Object[]{ex.getCodigo()};
        String message = messageSource.getMessage("exception.clienteNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(ClienteCpfNotFoundException.class)
    public ResponseEntity<ErrorMessage> clienteCpfNotFoundException(ClienteCpfNotFoundException ex, HttpServletRequest request) {
        Object[] params = new Object[]{ex.getRecurso(), ex.getCodigo()};
        String message = messageSource.getMessage("exception.clienteCpfNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(UsernameUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> usernameUniqueViolationException(UsernameUniqueViolationException ex, HttpServletRequest request) {
        String message;
        Object[] params;

        if (ex.getRecurso() == null || ex.getRecurso().isEmpty()) {
            params = new Object[]{ex.getCodigo()};
            message = messageSource.getMessage("exception.usernameUniqueViolationException", params, request.getLocale());
        }
        params = new Object[]{ex.getRecurso(), ex.getCodigo()};
        message = messageSource.getMessage("exception.usuarioUsernameUniqueViolationException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, message));
    }

    @ExceptionHandler(CpfUniqueViolationException.class)
    public ResponseEntity<ErrorMessage> cpfUniqueViolationException(CpfUniqueViolationException ex, HttpServletRequest request) {
        Object[] params = new Object[]{ex.getRecurso(), ex.getCodigo()};
        String message = messageSource.getMessage("exception.cpfUniqueViolationException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request,
            BindingResult result) {
        log.error("Api Error - ", ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY,
                        messageSource.getMessage("message.invalid.field", null, request.getLocale()),
                        result, messageSource));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        String message = messageSource.getMessage("message.invalid.credential", null, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, message));
    }

    @ExceptionHandler(RelatorioVazioException.class)
    public ResponseEntity<ErrorMessage> relatorioVazioException(RelatorioVazioException ex, HttpServletRequest request) {
        ErrorMessage error = new ErrorMessage(
                request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        log.error("Relatório Vazio Error {} {} - ", error, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerErrorException(Exception ex, HttpServletRequest request) {
        ErrorMessage error = new ErrorMessage(
                request, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        log.error("Internal Server Error {} {} - ", error, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}
