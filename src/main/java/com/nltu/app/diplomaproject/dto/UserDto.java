package com.nltu.app.diplomaproject.dto;

import java.util.List;

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    List<QuestionDto> questionDtos;

    public UserDto(String firstName, String lastName, String email, List<QuestionDto> questionDtos) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.questionDtos = questionDtos;
    }
    public UserDto() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<QuestionDto> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(List<QuestionDto> questionDtos) {
        this.questionDtos = questionDtos;
    }
}
