package com.nltu.app.diplomaproject.dto;

import java.util.List;

public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    List<QuestionUserParticipatedDto> questionsUserParticipated;

    public UserDto(String firstName,
                   String lastName,
                   String email,
                   List<QuestionUserParticipatedDto> questionsUserParticipated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.questionsUserParticipated = questionsUserParticipated;
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

    public List<QuestionUserParticipatedDto> getQuestionsUserParticipated() {
        return questionsUserParticipated;
    }

    public void setQuestionsUserParticipated(List<QuestionUserParticipatedDto> questionsUserParticipated) {
        this.questionsUserParticipated = questionsUserParticipated;
    }
}
