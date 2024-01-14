package com.nltu.app.diplomaproject.service.imp;

import com.nltu.app.diplomaproject.config.JwtService;
import com.nltu.app.diplomaproject.dto.AuthenticationResponse;
import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.dto.QuestionUserParticipatedDto;
import com.nltu.app.diplomaproject.dto.UserDto;
import com.nltu.app.diplomaproject.dto.UserLoginDto;
import com.nltu.app.diplomaproject.dto.UserRegistrationDto;
import com.nltu.app.diplomaproject.entity.User;
import com.nltu.app.diplomaproject.enums.Role;
import com.nltu.app.diplomaproject.exceptions.EmailAlreadyTakenException;
import com.nltu.app.diplomaproject.exceptions.ExceptionMessage;
import com.nltu.app.diplomaproject.repository.QuestionRepo;
import com.nltu.app.diplomaproject.repository.UserRepo;
import com.nltu.app.diplomaproject.service.UserService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final QuestionRepo questionRepo;

    public UserServiceImpl(UserRepo userRepo,
                           PasswordEncoder passwordEncoder,
                           JwtService jwtService,
                           AuthenticationManager authenticationManager,
                           ModelMapper modelMapper, QuestionRepo questionRepo) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.questionRepo = questionRepo;
    }

    @Override
    public String registerNewUser(UserRegistrationDto registrationDto) {
        userRepo.findByEmail(registrationDto.getEmail())
                .ifPresent(a->{throw new EmailAlreadyTakenException(ExceptionMessage.EMAIL_ALREADY_TAKEN);});
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

    public static User getAuthenticatedUser(UserRepo userRepo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepo.findByEmail(currentPrincipalName).orElseThrow(()->
                new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND));
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->
                new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND));
        UserDto result = modelMapper.map(user, UserDto.class);
        List<QuestionUserParticipatedDto> questionDtos = questionRepo.findAllByUserParticipated(id)
                .stream().map(q->modelMapper.map(q, QuestionUserParticipatedDto.class)).toList();
        result.setQuestionsUserParticipated(questionDtos);
        return result;
    }

    @Override
    public Page<UserDto> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable)
                .map(user -> {
                    UserDto userDto = modelMapper.map(user, UserDto.class);
                    List<QuestionUserParticipatedDto> questionDtos = questionRepo.findAllByUserParticipated(user.getId())
                            .stream().map(q -> modelMapper.map(q, QuestionUserParticipatedDto.class)).toList();
                    userDto.setQuestionsUserParticipated(questionDtos);
                    return userDto;
                });
    }

    @Override
    public void suspendUser(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND));

        user.setEnabled(false);
        userRepo.save(user);
    }

    @Override
    public void activateUser(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(()->
                new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND));

        user.setEnabled(true);
        userRepo.save(user);
    }
}
