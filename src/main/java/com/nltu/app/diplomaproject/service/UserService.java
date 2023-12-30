package com.nltu.app.diplomaproject.service;

import com.nltu.app.diplomaproject.dto.AuthenticationResponse;
import com.nltu.app.diplomaproject.dto.UserDto;
import com.nltu.app.diplomaproject.dto.UserLoginDto;
import com.nltu.app.diplomaproject.dto.UserRegistrationDto;
import com.nltu.app.diplomaproject.entity.User;

public interface UserService{


    /**
     * Method register user {@link User}.
     *
     * @param registrationDto a value of {@link UserRegistrationDto}
     * @author Yurii Midianyi
     */
    String registerNewUser(UserRegistrationDto registrationDto);

    /**
     * Method authenticate user {@link User}.
     *
     * @param userLoginDto a value of {@link UserLoginDto}
     * @author Yurii Midianyi
     */
    AuthenticationResponse authenticate(UserLoginDto userLoginDto);
}
