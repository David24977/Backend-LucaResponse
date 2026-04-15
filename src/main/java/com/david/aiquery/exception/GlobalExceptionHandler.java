package com.david.aiquery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        String message = ex.getBindingResult()
                .getFieldErrors()
                .getFirst()
                .getDefaultMessage();

        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(message, 400));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {

        System.err.println("Error no controlado: " + ex.getMessage());

        String message = ex.getMessage() != null ? ex.getMessage().toLowerCase() : "";

        if (message.contains("429") ||
                message.contains("quota") ||
                message.contains("rate limit") ||
                message.contains("resource exhausted")) {

            return ResponseEntity
                    .status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(new ErrorResponse(
                            "LucaResponse ha alcanzado el límite de uso de la demo. Inténtalo más tarde.",
                            429));
        }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(
                        "Error interno en el servidor de LucaResponse",
                        500));
    }
}