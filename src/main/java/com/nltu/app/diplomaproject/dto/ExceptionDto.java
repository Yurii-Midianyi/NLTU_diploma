package com.nltu.app.diplomaproject.dto;

public class ExceptionDto {
    private final String message;

    public ExceptionDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
