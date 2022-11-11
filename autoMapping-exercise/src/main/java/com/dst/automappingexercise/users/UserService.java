package com.dst.automappingexercise.users;

import com.dst.automappingexercise.users.dtos.UserLoginDTO;
import com.dst.automappingexercise.users.dtos.UserRegisterDTO;

import java.util.Optional;

public interface UserService {
    User register(UserRegisterDTO registerData);
    Optional<User> login(UserLoginDTO loginData);
    void logout();

    User getLoggedUser();
}
