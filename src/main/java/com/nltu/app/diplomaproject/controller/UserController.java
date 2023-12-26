package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.entity.User;
import com.nltu.app.diplomaproject.repository.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/get")
    public String getUsers(){
        return "success";
    }



    @GetMapping("/test")
    public String addUser() {
        // Create a new user
        User newUser = new User();
        newUser.setEmail("testUser");
        newUser.setPassword("pass");
        // Save the user to the database
        userRepo.save(newUser);
        return "User added successfully!";
    }

}
