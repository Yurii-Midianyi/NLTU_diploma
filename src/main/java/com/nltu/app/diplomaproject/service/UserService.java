package com.nltu.app.diplomaproject.service;

import com.nltu.app.diplomaproject.dto.UserDto;
import com.nltu.app.diplomaproject.entity.User;

public interface UserService{

    /**
     * Method that allows you to save new {@link User}.
     *
     * @param user a value of {@link User}
     * @author Yurii Midianyi
     */
    UserDto save (User user);
}
