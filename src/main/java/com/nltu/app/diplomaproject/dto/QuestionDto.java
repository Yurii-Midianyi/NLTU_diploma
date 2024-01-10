package com.nltu.app.diplomaproject.dto;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    private Long id;
    private String questionText;
    private List<AnswerDto> answers;
    private Boolean isAnonymous;
    private LocalDateTime endDateTime;

    public QuestionDto() {}
    public QuestionDto(Long id, String questionText, List<AnswerDto> answers, Boolean isAnonymous, LocalDateTime endDateTime) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.isAnonymous = isAnonymous;
        this.endDateTime = endDateTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
