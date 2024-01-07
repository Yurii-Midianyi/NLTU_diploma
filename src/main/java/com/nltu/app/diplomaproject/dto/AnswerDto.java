package com.nltu.app.diplomaproject.dto;

public class AnswerDto {
    private Long id;
    private String answerText;

    public AnswerDto() {}

    public AnswerDto(Long id, String answerText) {
        this.id = id;
        this.answerText = answerText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
