package com.nltu.app.diplomaproject.dto;

public class AnswerResultDto {
    private String answerText;
    private Long count;

    public AnswerResultDto(String answerText, Long count) {
        this.answerText = answerText;
        this.count = count;
    }

    public AnswerResultDto(){}

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
