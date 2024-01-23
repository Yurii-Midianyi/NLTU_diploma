package com.nltu.app.diplomaproject.exceptions;

public class MoreThanOneAnswerToQuestionException extends RuntimeException{
    public MoreThanOneAnswerToQuestionException(String message){
        super(message);
    }

}
