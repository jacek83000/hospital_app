package com.example.hospital_app_server.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(ResourceNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), getErrorsMap(e));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), getErrorsMap(e));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), getErrorsMap(e));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), getErrorsMap(e));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Map<String, String> getErrorsMap(Exception e) {
        Map<String, String> errorsMap = new HashMap<>();
        if (e instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) e)
                    .getBindingResult()
                    .getFieldErrors()
                    .forEach(error -> errorsMap.put(error.getField(), error.getDefaultMessage()));
        } else {
            errorsMap.put("error", e.getMessage());
        }
        return errorsMap;
    }
}
