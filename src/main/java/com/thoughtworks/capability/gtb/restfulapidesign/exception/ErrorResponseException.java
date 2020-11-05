package com.thoughtworks.capability.gtb.restfulapidesign.exception;

import org.springframework.http.HttpStatus;

public abstract class ErrorResponseException extends RuntimeException {

    public abstract HttpStatus getCode();
    public abstract String getMessage();
}
