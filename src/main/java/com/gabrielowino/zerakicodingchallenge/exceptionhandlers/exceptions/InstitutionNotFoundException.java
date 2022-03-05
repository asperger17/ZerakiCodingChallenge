package com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstitutionNotFoundException extends RuntimeException{

    public InstitutionNotFoundException(String registrationNumber) {
        super("Unfortunately the Institution with the registration number " + registrationNumber +
                "could not be found.");
    }
}
