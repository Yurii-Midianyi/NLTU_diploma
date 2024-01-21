package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.dto.EmailDto;
import com.nltu.app.diplomaproject.dto.UserDto;
import com.nltu.app.diplomaproject.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllQuestions(Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    @PatchMapping("/suspend")
    public ResponseEntity<Void> suspendUser(@RequestBody EmailDto emailDto){
        userService.suspendUser(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/activate")
    public ResponseEntity<Void> activateUser(@RequestBody EmailDto emailDto){
        userService.activateUser(emailDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
