package com.nltu.app.diplomaproject.exceptions;

import com.nltu.app.diplomaproject.dto.ExceptionDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {QuestionNotFoundException.class})
    public ResponseEntity<Object> handleRequestException(QuestionNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }

    @ExceptionHandler(value = {AnswerNotFoundException.class})
    public ResponseEntity<Object> handleAnswerNotFoundException(AnswerNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }
    @ExceptionHandler(value = {NonExistingAnswerForQuestionException.class})
    public ResponseEntity<Object> handleNonExistingAnswerForQuestionException(NonExistingAnswerForQuestionException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionDto);
    }

    @ExceptionHandler(value = {EmailAlreadyTakenException.class})
    public ResponseEntity<Object> handleEmailAlreadyTaken(EmailAlreadyTakenException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDto);
    }

    @ExceptionHandler(value = {VotingPeriodEndedException.class})
    public ResponseEntity<Object> handleVotingPeriodEndedException(VotingPeriodEndedException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    @ExceptionHandler(value = {DisabledException.class})
    public ResponseEntity<Object> handleDisabledException(DisabledException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionDto);
    }

    @ExceptionHandler(value = {CustomAccessDeniedException.class})
    public ResponseEntity<Object> handleCustomAccessDeniedException(CustomAccessDeniedException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionDto);
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class, ConstraintViolationException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(){
        ExceptionDto exceptionDto = new ExceptionDto(ExceptionMessage.WRONG_FORMAT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

    @ExceptionHandler(value = {MoreThanOneAnswerToQuestionException.class})
    public ResponseEntity<Object> handleMoreThanOneAnswerToQuestionException(MoreThanOneAnswerToQuestionException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDto);
    }

}
