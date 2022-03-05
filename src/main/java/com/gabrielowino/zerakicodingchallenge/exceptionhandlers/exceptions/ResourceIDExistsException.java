package com.gabrielowino.zerakicodingchallenge.exceptionhandlers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class ResourceIDExistsException extends RuntimeException{

    public ResourceIDExistsException(String resource, String resourceName, String resourceID) {
        super("Unfortunately another resource: " + resource + " with the same " + resourceName +
                " : " + resourceID + "already exists. Please try again with a different " +
                resourceName + ".");
    }
}
