package com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InstitutionRegistrationNumberExistsException extends RuntimeException{

    public InstitutionRegistrationNumberExistsException(String registrationNumber) {
        super("Unfortunately another Institution with the name " + registrationNumber +
                "already exists. Please try again with a different name.");
    }
}
