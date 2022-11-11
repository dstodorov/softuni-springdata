package com.dst.automappingexercise.util.validators;

import com.dst.automappingexercise.util.exceptions.UserValidationException;

import static com.dst.automappingexercise.util.validators.ValidationMessages.*;

public class UserDataValidator {


    public static void validateUserRegistrationData(String email, String password, String confirmPassword) {
        validateEmail(email);
        validatePassword(password);
        validateConfirmPassword(password, confirmPassword);
    }

    public static void validateUserLoginData(String email, String password){
        validateEmail(email);
        validatePassword(password);
    }

    private static void validateEmail(String email) {
        if (!email.contains("@") || !email.contains(".")) {
            throw new UserValidationException(WRONG_EMAIL_MESSAGE);
        }
    }

    private static void validatePassword(String password) {
        if (password.length() < 6 && password.chars().noneMatch(Character::isUpperCase)
                || password.chars().noneMatch(Character::isLowerCase)
                || password.chars().noneMatch(Character::isDigit)) {
            throw new UserValidationException(WRONG_PASSWORD_MESSAGE);
        }
    }

    private static void validateConfirmPassword(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new UserValidationException(WRONG_CONFIRM_PASSWORD_MESSAGE);
        }
    }
}
