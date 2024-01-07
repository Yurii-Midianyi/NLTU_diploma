package com.nltu.app.diplomaproject.repository;

import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.entity.QuestionParticipant;
import com.nltu.app.diplomaproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuestionParticipantRepo extends JpaRepository<QuestionParticipant, Long> {
    public Boolean existsByUserAndQuestion(User user, Question question);
    public void deleteAllByUserAndQuestion(User user, Question question);
}
