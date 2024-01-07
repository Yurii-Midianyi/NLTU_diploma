package com.nltu.app.diplomaproject.exceptions;

public class NonExistingAnswerForQuestionException extends RuntimeException{
    public NonExistingAnswerForQuestionException(String message){
        super(message);
    }
}
