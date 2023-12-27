package com.nltu.app.diplomaproject.service;
import com.nltu.app.diplomaproject.entity.User;


public interface UserService /*extends UserDetailsService*/ {

    /**
     * Method that allows you to save new {@link User}.
     *
     * @param user a value of {@link User}
     * @author Yurii Midianyi
     */
    User save (User user);
}
