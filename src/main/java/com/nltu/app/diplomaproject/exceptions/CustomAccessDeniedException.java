package com.nltu.app.diplomaproject.exceptions;


public class CustomAccessDeniedException extends RuntimeException{
    public CustomAccessDeniedException(String message) {
        super(message);
    }
}
