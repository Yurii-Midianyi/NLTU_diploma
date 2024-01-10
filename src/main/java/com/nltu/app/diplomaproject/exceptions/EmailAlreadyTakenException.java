package com.nltu.app.diplomaproject.exceptions;

public class EmailAlreadyTakenException extends RuntimeException{
    public EmailAlreadyTakenException(String message){
        super(message);
    }
}
