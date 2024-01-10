package com.nltu.app.diplomaproject.dto;

import com.nltu.app.diplomaproject.exceptions.ExceptionMessage;
import jakarta.validation.constraints.NotBlank;


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
