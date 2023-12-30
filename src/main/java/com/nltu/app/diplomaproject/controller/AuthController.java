package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.dto.AuthenticationResponse;
import com.nltu.app.diplomaproject.dto.UserLoginDto;
import com.nltu.app.diplomaproject.dto.UserRegistrationDto;
import com.nltu.app.diplomaproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRegistrationDto registrationDto) {
        return ResponseEntity.ok(userService.registerNewUser(registrationDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(@RequestBody UserLoginDto loginDto) {
        return ResponseEntity.ok(userService.authenticate(loginDto));
    }
}
