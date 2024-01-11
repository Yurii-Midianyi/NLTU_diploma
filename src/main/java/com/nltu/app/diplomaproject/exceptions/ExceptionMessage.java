package com.nltu.app.diplomaproject.exceptions;

public abstract class ExceptionMessage {
    public static final String QUESTION_NOT_FOUND = "Question was not found";
    public static final String USER_NOT_FOUND = "User was not found";
    public static final String ANSWER_NOT_FOUND = "Answer was not found";
    public static final String WRONG_ANSWER_ID = "Wrong answer id";
    public static final String VOTING_PERIOD_ENDED = "Voting period for this question has ended" ;
    public static final String PASSWORD_VALIDATION_FAIL =
            "Password must contain at least one alphabetical character and be at least 8 characters long";
    public static final String FIRSTNAME_VALIDATION_FAIL = "First name can not be empty";
    public static final String LASTNAME_VALIDATION_FAIL = "Last name can not be empty";
    public static final String EMAIL_VALIDATION_FAIL = "Please provide a valid email address";
    public static final String ANSWER_VALIDATION_FAIL = "Answer can not be empty";
    public static final String QUESTION_VALIDATION_FAIL = "Question can not be empty";
    public static final String END_DATE_VALIDATION_FAIL = "End date must be in the future";
    public static final String EMAIL_ALREADY_TAKEN = "Email is already registered";
}
