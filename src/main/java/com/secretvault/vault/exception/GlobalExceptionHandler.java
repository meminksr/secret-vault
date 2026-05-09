package com.secretvault.vault.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Tertemiz bir JSON formatı
        Map<String, Object> errorResponse = new HashMap<>();

        errorResponse.put("durum", "BAŞARISIZ");
        errorResponse.put("kod", 400);

        // Entity'de @NotBlank içine yazdığımız o özel mesajı cımbızla çekiyoruz
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        errorResponse.put("mesaj", errorMessage);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}