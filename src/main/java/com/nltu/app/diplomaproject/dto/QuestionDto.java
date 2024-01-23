package com.nltu.app.diplomaproject.dto;

import com.nltu.app.diplomaproject.annotations.ListMaxSize;
import com.nltu.app.diplomaproject.annotations.NotEmptyAnswers;
import com.nltu.app.diplomaproject.exceptions.ExceptionMessage;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    private Long id;
    @NotBlank(message = ExceptionMessage.QUESTION_VALIDATION_FAIL)
    private String questionText;
    @NotEmptyAnswers
    @ListMaxSize(message = ExceptionMessage.ANSWER_SIZE_FAIL)
    private List<AnswerDto> answers;
    private Boolean isPrivate;
    @Future(message = ExceptionMessage.END_DATE_VALIDATION_FAIL)
    private LocalDateTime endDateTime;
    private Boolean hasMultipleAnswers;

    public QuestionDto() {}
    public QuestionDto(Long id,
                       String questionText,
                       List<AnswerDto> answers,
                       Boolean isPrivate,
                       LocalDateTime endDateTime,
                       Boolean hasMultipleAnswers) {
        this.id = id;
        this.questionText = questionText;
        this.answers = answers;
        this.isPrivate = isPrivate;
        this.endDateTime = endDateTime;
        this.hasMultipleAnswers = hasMultipleAnswers;
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

    public Boolean getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Boolean anonymous) {
        isPrivate = anonymous;
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

    public Boolean getHasMultipleAnswers() {
        return hasMultipleAnswers;
    }

    public void setHasMultipleAnswers(Boolean hasMultipleAnswers) {
        this.hasMultipleAnswers = hasMultipleAnswers;
    }
}
