package com.nltu.app.diplomaproject.service;

import com.nltu.app.diplomaproject.dto.PollResultsDto;
import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Question;
import java.nio.file.AccessDeniedException;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    Page<QuestionDto> getAllQuestions(Pageable pageable);

    QuestionDto create(Question question);

    QuestionDto getQuestion(Long id);

    void deleteQuestion(Long id);

    QuestionDto updateQuestion(Long id, QuestionDto questionDto);

    String chooseVoting(Long questionId, List<Long> answerIds);

    String voteQuestion(Question question, List<Long> answerIds);

    String voteQuestion(Question question, Long answerId);

    PollResultsDto getResults(Long id);

    String cancelVote(Long questionId);
}
