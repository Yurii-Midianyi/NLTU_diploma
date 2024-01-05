package com.nltu.app.diplomaproject.exceptions;

import com.nltu.app.diplomaproject.dto.ExceptionDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {RequestException.class})
    public ResponseEntity<Object> handleRequestException(RequestException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }


    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(){
        ExceptionDto exceptionDto = new ExceptionDto("The object was not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDto);
    }
}
