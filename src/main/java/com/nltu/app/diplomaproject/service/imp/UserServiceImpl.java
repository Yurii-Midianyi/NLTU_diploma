package com.nltu.app.diplomaproject.service.imp;

import com.nltu.app.diplomaproject.config.JwtService;
import com.nltu.app.diplomaproject.dto.AuthenticationResponse;
import com.nltu.app.diplomaproject.dto.UserLoginDto;
import com.nltu.app.diplomaproject.dto.UserRegistrationDto;
import com.nltu.app.diplomaproject.entity.User;
import com.nltu.app.diplomaproject.enums.Role;
import com.nltu.app.diplomaproject.repository.UserRepo;
import com.nltu.app.diplomaproject.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String registerNewUser(UserRegistrationDto registrationDto) {
        var user = new User.Builder()
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
        return "User successfully registered";
    }

    @Override
    public AuthenticationResponse authenticate(UserLoginDto userLoginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginDto.getEmail(), userLoginDto.getPassword()
        ));
        var user = userRepo.findByEmail(userLoginDto.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}
