package com.nltu.app.diplomaproject.dto;

import com.nltu.app.diplomaproject.exceptions.ExceptionMessage;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


public class AnswerDto {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, example = "1")
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
