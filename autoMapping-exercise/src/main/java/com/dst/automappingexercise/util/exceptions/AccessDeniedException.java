package com.dst.automappingexercise.util.exceptions;

public class AccessDeniedException extends DataValidationException{
    public AccessDeniedException(String message) {
        super(message);
    }
}
