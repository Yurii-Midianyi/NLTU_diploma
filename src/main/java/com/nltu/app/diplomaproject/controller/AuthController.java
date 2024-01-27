package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.constants.HttpStatuses;
import com.nltu.app.diplomaproject.dto.AnswerDto;
import com.nltu.app.diplomaproject.dto.AuthenticationResponse;
import com.nltu.app.diplomaproject.dto.UserLoginDto;
import com.nltu.app.diplomaproject.dto.UserRegistrationDto;
import com.nltu.app.diplomaproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "register as user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
    })
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "user email and password",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserRegistrationDto.class), examples = {
                            @ExampleObject(value = "{\"email\":\"someemail@gmail.com\"," +
                                                    "\"password\":\"password123\","+
                                                    "\"firstName\":\"John\"," +
                                                    "\"lastName\":\"Doe\"}")
                    }))
            @Valid @RequestBody UserRegistrationDto registrationDto,
                                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResponseEntity.ok(userService.registerNewUser(registrationDto));
    }

    @Operation(summary = "login in")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
    })
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signIn(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "user email and password",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserLoginDto.class), examples = {
                            @ExampleObject(value = "{\"email\":\"someemail@gmail.com\"," +
                                                    "\"password\":\"password123\"}")
                    }))
            @Valid @RequestBody UserLoginDto loginDto) {
        return ResponseEntity.ok(userService.authenticate(loginDto));
    }
}
