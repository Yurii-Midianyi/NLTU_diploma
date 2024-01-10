package com.nltu.app.diplomaproject.annotations;

import com.nltu.app.diplomaproject.dto.AnswerDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyElementsValidator implements ConstraintValidator<NotEmptyAnswers, List<AnswerDto>> {

    @Override
    public void initialize(NotEmptyAnswers constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<AnswerDto> answerDtos, ConstraintValidatorContext constraintValidatorContext) {
        return answerDtos != null && answerDtos.stream().map(AnswerDto::getAnswerText).noneMatch(String::isEmpty);
    }
}
