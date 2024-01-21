package com.nltu.app.diplomaproject.repository;

import com.nltu.app.diplomaproject.entity.Question;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q JOIN q.answers a WHERE a.id = :answerId")
    public Question findByAnswerId(Long answerId);

    @Query("SELECT q FROM Question q join q.participants qp where qp.id =:userId")
    public List<Question> findAllByUserParticipated(Long userId);

    //to load organizer because he is in lazy loading
    @Query("SELECT q FROM Question q JOIN FETCH q.organizer WHERE q.id = :questionId")
    Optional<Question> findQuestionWithOrganizer(@Param("questionId") Long questionId);
}
