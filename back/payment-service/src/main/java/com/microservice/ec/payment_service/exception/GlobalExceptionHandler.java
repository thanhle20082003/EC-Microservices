package com.microservice.ec.payment_service.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArchitectureException.class)
    public ErrorResponse handleArchitectureException(ArchitectureException ex, HttpServletRequest request) {

        return ErrorResponse.builder()
                .code(ex.getErrorCode().getCode())
                .error(ex.getErrorCode().name())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(400)
                .error("VALIDATION_ERROR")
                .message("Validation failed: " + String.join(", ", errors))
                .path(request.getRequestURI())
                .build();
    }
}
