package com.nltu.app.diplomaproject.dto;

public class QuestionUserParticipatedDto {
    private Long id;
    private String questionText;

    public QuestionUserParticipatedDto(Long id, String questionText) {
        this.id = id;
        this.questionText = questionText;
    }
    public QuestionUserParticipatedDto(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
