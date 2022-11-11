package com.dst.automappingexercise.util.exceptions;

public class GameNotFoundException extends DataValidationException{
    public GameNotFoundException(String message) {
        super(message);
    }
}
