package com.nltu.app.diplomaproject.dto;

public class EmailDto {
    private String email;

    EmailDto(){}

    public EmailDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
