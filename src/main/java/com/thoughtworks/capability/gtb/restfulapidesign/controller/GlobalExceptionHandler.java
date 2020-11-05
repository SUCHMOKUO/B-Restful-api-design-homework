package com.thoughtworks.capability.gtb.restfulapidesign.controller;

import com.thoughtworks.capability.gtb.restfulapidesign.dto.ErrorResponse;
import com.thoughtworks.capability.gtb.restfulapidesign.exception.ErrorResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ErrorResponseException.class)
    public ResponseEntity<ErrorResponse> handleException(ErrorResponseException ex) {
        return ResponseEntity
                .status(ex.getCode())
                .body(new ErrorResponse(ex.getMessage()));
    }
}
