package com.nltu.app.diplomaproject.service.imp;


import com.nltu.app.diplomaproject.entity.Answer;
import com.nltu.app.diplomaproject.repository.AnswerRepo;
import com.nltu.app.diplomaproject.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepo answerRepo;

    public AnswerServiceImpl(AnswerRepo answerRepo) {
        this.answerRepo = answerRepo;
    }

    @Override
    public Answer create(Answer answer) {
        return answerRepo.save(answer);
    }
}
