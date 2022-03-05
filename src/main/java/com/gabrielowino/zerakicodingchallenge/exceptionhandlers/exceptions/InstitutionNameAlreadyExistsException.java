package com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InstitutionNameAlreadyExistsException extends RuntimeException{

    public InstitutionNameAlreadyExistsException(String institutionName) {
        super("Unfortunately another Institution with the name " + institutionName +
                "already exists. Please try again with a different name.");
    }
}
