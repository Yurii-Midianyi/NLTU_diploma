package com.nltu.app.diplomaproject.dto;

public class AnswerDto {
    private String answerText;

    public AnswerDto() {}

    public AnswerDto(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
