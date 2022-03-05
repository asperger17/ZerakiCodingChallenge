package com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resource, String resourceIDName, String resourceID) {
        super("Unfortunately the resource : " + resource + "  with " + resourceIDName + " : " + resourceID +
                "could not be found.");
    }
}
