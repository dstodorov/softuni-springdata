package com.dst.automappingexercise.users;


import com.dst.automappingexercise.util.exceptions.DataValidationException;
import com.dst.automappingexercise.util.exceptions.NoLoggedUserFoundException;
import com.dst.automappingexercise.util.exceptions.UserValidationException;
import com.dst.automappingexercise.users.dtos.UserLoginDTO;
import com.dst.automappingexercise.users.dtos.UserRegisterDTO;
import com.dst.automappingexercise.util.validators.ValidationMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private User currentUser;
    public static boolean isAdmin;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.currentUser = null;
        isAdmin = false;
        this.userRepository = userRepository;
    }

    @Override
    public User register(UserRegisterDTO registerData) {
        ModelMapper mapper = new ModelMapper();
        User toRegister = mapper.map(registerData, User.class);

        long userCount = this.userRepository.count();

        if (userCount == 0) toRegister.setAdmin(true);

        Optional<User> user = this.userRepository.findByEmail(toRegister.getEmail());

        if (user.isPresent()) {
            throw new UserValidationException(String.format(ValidationMessages.USER_EXISTS, registerData.getEmail()));
        }

        return this.userRepository.save(toRegister);
    }

    @Override
    public Optional<User> login(UserLoginDTO loginData) {
        Optional<User> user = this.userRepository.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());

        if (user.isPresent() && currentUser != null && user.get().getEmail().equals(currentUser.getEmail())) {
            throw new DataValidationException(String.format("%s is already logged in", user.get().getEmail()));
        }


        user.ifPresent(value -> {
            this.currentUser = value;
            isAdmin = user.get().getAdmin();
        });

        return user;
    }

    @Override
    public void logout() {
        if (currentUser == null) {
            throw new NoLoggedUserFoundException("Cannot log out. No user was logged in.");
        }

        this.currentUser = null;
    }

    @Override
    public User getLoggedUser() {
        return this.currentUser;
    }
}
