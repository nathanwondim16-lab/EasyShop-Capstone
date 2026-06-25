package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yearup.exceptions.CategoryNotFoundException;
import org.yearup.exceptions.InvalidCategoryException;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRegisteredAttempts(
            MethodArgumentNotValidException exception
    ) {

        var errors = new HashMap<String, String>();

        exception.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<String> handleCategoryNotFound(CategoryNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<String> handleInvalidCategory(InvalidCategoryException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }
}