package com.project.learningmanagemtsystem.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errorMessageBuilder.append(fieldName).append(": ").append(errorMessage).append(". ");
        });

        return new ResponseEntity<>(errorMessageBuilder.toString(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
        return new ResponseEntity<>(ex.getReason(), ex.getStatusCode());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Data Integrity Violation: " + ex.getMessage();

        logger.error("Data Integrity Violation Exception: {}", ex.getMessage());

        if (ex.getMessage().contains("uk9q63snka3mdh91as4io72espi")) {
            message = "Phone Number already exists";
        } else if (ex.getMessage().contains("unique constraint") && ex.getMessage().contains("email")) {
            message = "Email already exists";
        }else if (ex.getMessage().contains("unique constraint") && ex.getMessage().contains("username")) {
            message = "Username already exists";
        } else {
            message = "A unique constraint was violated";
        }

        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
