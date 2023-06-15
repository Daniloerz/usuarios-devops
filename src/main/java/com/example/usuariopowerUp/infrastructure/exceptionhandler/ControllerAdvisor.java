package com.example.usuariopowerUp.infrastructure.exceptionhandler;

import com.example.usuariopowerUp.domain.exception.ValidationException;
import com.example.usuariopowerUp.infrastructure.exception.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "message";

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoDataFoundException(
            NoDataFoundException noDataFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, noDataFoundException.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            ValidationException validationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, validationException.getMessage()));
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<String> handleRuntimeException(
            RuntimeException runtimeException) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(runtimeException.getMessage());
    }
    
}