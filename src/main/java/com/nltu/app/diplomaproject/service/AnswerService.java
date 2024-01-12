package com.nltu.app.diplomaproject.service;

import com.nltu.app.diplomaproject.dto.AnswerDto;
import com.nltu.app.diplomaproject.entity.Answer;

public interface AnswerService {
    Answer create(Answer answer);
    AnswerDto updateAnswer(Long id, AnswerDto answerDto);
    void deleteAnswer(Long id);
}
