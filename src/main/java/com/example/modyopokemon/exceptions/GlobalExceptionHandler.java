package com.example.modyopokemon.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<Void> handleHttpClientNotFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Void> handleHttpClientError() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Void> handleHttpServerError() {
        return ResponseEntity.internalServerError().build();
    }
}
