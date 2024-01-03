package com.nltu.app.diplomaproject.service;

import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Question;
import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();

    QuestionDto create(Question question);

    QuestionDto getQuestion(Long id);
}
