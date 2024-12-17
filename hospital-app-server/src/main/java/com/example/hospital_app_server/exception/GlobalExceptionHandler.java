package com.example.hospital_app_server.exception;

import jakarta.validation.ConstraintViolationException;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
        return new ResponseEntity<>(new ErrorResponse(getErrors(e)), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(new ErrorResponse(getErrors(e)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(new ErrorResponse(getErrors(e)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(ConstraintViolationException e) {
        return new ResponseEntity<>(new ErrorResponse(getErrors(e)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormatException(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ErrorResponse(getErrors(e)), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
//        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), getErrors(e));
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
//        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), getErrors(e));
//        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    private Map<String, String> getErrors(Exception e) {
        Map<String, String> errors = new HashMap<>();
        if (e instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) e)
                    .getBindingResult()
                    .getFieldErrors()
                    .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        } else if (e instanceof ConstraintViolationException) {
            ((ConstraintViolationException) e)
                    .getConstraintViolations()
                    .forEach(cv -> errors.put(cv.getPropertyPath().toString(), cv.getMessage()));
        } else {
            errors.put("error", e.getMessage());
        }
        return errors;
    }
}
