package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.entity.User;
import com.nltu.app.diplomaproject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
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
        userService.save(newUser);
        return "User added successfully!";
    }

}
