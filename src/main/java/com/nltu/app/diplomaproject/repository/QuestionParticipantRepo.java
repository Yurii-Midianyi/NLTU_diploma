package com.nltu.app.diplomaproject.repository;

import com.nltu.app.diplomaproject.dto.AnswerResultDto;
import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.entity.QuestionParticipant;
import com.nltu.app.diplomaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface QuestionParticipantRepo extends JpaRepository<QuestionParticipant, Long> {
    public Boolean existsByUserAndQuestion(User user, Question question);
    public void deleteAllByUserAndQuestion(User user, Question question);
    public Long countByQuestionId(Long id);

    @Query("SELECT NEW com.nltu.app.diplomaproject.dto.AnswerResultDto(qp.answer.answerText, COUNT(qp.answer)) " +
            "FROM QuestionParticipant qp " +
            "WHERE qp.question.id = :questionId " +
            "GROUP BY qp.answer.answerText")
    List<AnswerResultDto> countAnswerResults(Long questionId);

}
