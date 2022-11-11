package com.dst.automappingexercise.users.dtos;

import com.dst.automappingexercise.util.validators.UserDataValidator;

/**
 * Validate the data for registering a user.
 * <p>
 * commandParams[0] is skipped because it contains command name which is not needed
 * for creating user object
 *
 */

public class UserRegisterDTO {
    private final String email;
    private final String password;
    private final String confirmPassword;
    private final String fullName;

    public UserRegisterDTO(String[] commandParams) {
        this.email = commandParams[1];
        this.password = commandParams[2];
        this.confirmPassword = commandParams[3];
        this.fullName = commandParams[4];

        UserDataValidator.validateUserRegistrationData(this.email, this.password, this.confirmPassword);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }
}
