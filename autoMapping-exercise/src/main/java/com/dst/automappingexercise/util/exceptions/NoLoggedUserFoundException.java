package com.dst.automappingexercise.util.exceptions;

public class NoLoggedUserFoundException extends DataValidationException {
    public NoLoggedUserFoundException(String message) {
        super(message);
    }
}
