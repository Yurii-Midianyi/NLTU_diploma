package com.nltu.app.diplomaproject.exceptions;

public class VotingPeriodEndedException extends RuntimeException{
    public VotingPeriodEndedException(String message){
        super(message);
    }
}
