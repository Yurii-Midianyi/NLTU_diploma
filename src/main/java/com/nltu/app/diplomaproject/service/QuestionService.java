package com.nltu.app.diplomaproject.service;

import com.nltu.app.diplomaproject.dto.PollResultsDto;
import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.entity.User;

import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllQuestions();

    QuestionDto create(Question question);

    QuestionDto getQuestion(Long id);

    void deleteQuestion(Long id);

    QuestionDto updateQuestion(Long id, QuestionDto questionDto);

    String voteQuestion(Long questionId, List<Long> answerIds);

    PollResultsDto getResults(Long id);

    String cancelVote(Long questionId);
}
