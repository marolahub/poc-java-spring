package com.systembackpoc.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ExceptionsPoc.class)
    public ResponseEntity<StandardError> objectNotFound(ExceptionsPoc e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(
                System.currentTimeMillis(),
                status.value(),
                "Não encontrado",
                e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
