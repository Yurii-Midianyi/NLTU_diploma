package com.nltu.app.diplomaproject.dto;

import java.util.List;

public class QuestionDto {
    private String questionText;
    private List<AnswerDto> answers;
    private Boolean isAnonymous;

    public QuestionDto() {}
    public QuestionDto(String questionText, List<AnswerDto> answers, Boolean isAnonymous) {
        this.questionText = questionText;
        this.answers = answers;
        this.isAnonymous = isAnonymous;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public Boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }
}
