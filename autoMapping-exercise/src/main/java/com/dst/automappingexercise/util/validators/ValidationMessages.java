package com.dst.automappingexercise.util.validators;

public interface ValidationMessages {

    /*
    *
    * User validation messages
    *
    * */
    String WRONG_EMAIL_MESSAGE = "Email must contain '@' and '.' symbols!";
    String WRONG_PASSWORD_MESSAGE = "Password must contains at least one digit, one uppercase and one lowercase chars!";
    String WRONG_CONFIRM_PASSWORD_MESSAGE = "Passwords does not match!";
    String USER_EXISTS = "User with email %s already exists!";


    /*
     *
     * Game validation messages
     *
     * */
    String WRONG_GAME_TITLE_MESSAGE = "Title should start with uppercase letter and length should be between 3 and 100 symbols";
    String WRONG_GAME_PRICE_MESSAGE = "Price must positive number";
    String WRONG_GAME_SIZE_MESSAGE = "Size must be positive number";
    String WRONG_GAME_TRAILER_MESSAGE = "Game ID should be exactly 11 symbols";
    String WRONG_GAME_THUMBNAIL_MESSAGE = "URL should start with http:// or https://";
    String WRONG_GAME_DESCRIPTION_MESSAGE = "Description should be more than 20 symbols";
}
