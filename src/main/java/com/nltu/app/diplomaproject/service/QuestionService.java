package com.nltu.app.diplomaproject.service;

import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Question;
import java.util.List;

public interface QuestionService {
    List<Question> getAllQuestions();

    QuestionDto create(Question question);
}
