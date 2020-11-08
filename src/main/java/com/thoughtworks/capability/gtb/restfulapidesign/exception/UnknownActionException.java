package com.thoughtworks.capability.gtb.restfulapidesign.exception;

import org.springframework.http.HttpStatus;

public class UnknownActionException extends ErrorResponseException {

    @Override
    public HttpStatus getCode() {
        return HttpStatus.BAD_REQUEST;
    }

    @Override
    public String getMessage() {
        return "非法操作";
    }
}
