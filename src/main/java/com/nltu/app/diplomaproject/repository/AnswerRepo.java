package com.nltu.app.diplomaproject.repository;

import com.nltu.app.diplomaproject.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AnswerRepo extends JpaRepository<Answer, Long> {
    public void deleteAllByQuestionId(Long id);

    public List<Answer> findAllByQuestionId(Long id);
}
