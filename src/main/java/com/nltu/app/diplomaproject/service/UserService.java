package com.nltu.app.diplomaproject.service;

import com.nltu.app.diplomaproject.dto.AuthenticationResponse;
import com.nltu.app.diplomaproject.dto.UserDto;
import com.nltu.app.diplomaproject.dto.UserLoginDto;
import com.nltu.app.diplomaproject.dto.UserRegistrationDto;
import com.nltu.app.diplomaproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    /**
     * Method gets user {@link User} by id.
     *
     * @param id user's id
     * @author Yurii Midianyi
     */
    UserDto getUser(Long id);

    /**
     * Method gets pageable of user {@link User} by id.
     *
     * @param pageable of {@link Pageable}
     * @author Yurii Midianyi
     */
    Page<UserDto> getAllUsers(Pageable pageable);
}
