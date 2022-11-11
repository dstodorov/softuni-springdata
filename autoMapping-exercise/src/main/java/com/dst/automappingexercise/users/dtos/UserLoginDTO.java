package com.dst.automappingexercise.users.dtos;

import com.dst.automappingexercise.util.validators.UserDataValidator;

public class UserLoginDTO {
    private String email;
    private String password;
    public UserLoginDTO(String[] commandParams) {
        this.email = commandParams[1];
        this.password = commandParams[2];

        UserDataValidator.validateUserLoginData(this.email, this.password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
