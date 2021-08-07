package com.example.demo.controller;

import com.example.demo.model.error.ApiException;
import com.example.demo.model.error.ErrorCode;
import com.example.demo.model.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> traderException(ApiException e) {
        log.error("Error", e);
        return new ResponseEntity<>(new ErrorResponse(e.getErrorCode(), e.getErrorMessage()), e.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handle(MethodArgumentNotValidException ex) {
        log.error("Error ", ex);
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        String errorMessage = errors.stream().map(Object::toString).collect(Collectors.joining(", "));
        return new ResponseEntity<>(new ErrorResponse(ErrorCode.INVALID_REQUEST.getCode(), errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handle(HttpMessageNotReadableException ex) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(
                new ErrorResponse(ErrorCode.INVALID_REQUEST.getCode(), String.format("Invalid JSON, detailed info: %s", errorMessage)),
                HttpStatus.BAD_REQUEST);
    }
}
