package com.thoughtworks.capability.gtb.restfulapidesign.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;
}
