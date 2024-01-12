package com.nltu.app.diplomaproject.repository;

import com.nltu.app.diplomaproject.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    @Query("SELECT q FROM Question q JOIN q.answers a WHERE a.id = :answerId")
    public Question findByAnswerId(Long answerId);
}
