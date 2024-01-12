package com.nltu.app.diplomaproject.service.imp;

import com.nltu.app.diplomaproject.dto.AnswerDto;
import com.nltu.app.diplomaproject.entity.Answer;
import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.exceptions.AnswerNotFoundException;
import com.nltu.app.diplomaproject.exceptions.ExceptionMessage;
import com.nltu.app.diplomaproject.exceptions.QuestionNotFoundException;
import com.nltu.app.diplomaproject.repository.AnswerRepo;
import com.nltu.app.diplomaproject.repository.QuestionParticipantRepo;
import com.nltu.app.diplomaproject.repository.QuestionRepo;
import com.nltu.app.diplomaproject.repository.UserRepo;
import com.nltu.app.diplomaproject.service.AnswerService;
import static com.nltu.app.diplomaproject.service.imp.UserServiceImpl.getAuthenticatedUser;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepo answerRepo;
    private final QuestionParticipantRepo questionParticipantRepo;
    private final QuestionRepo questionRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public AnswerServiceImpl(AnswerRepo answerRepo,
                             QuestionParticipantRepo questionParticipantRepo,
                             QuestionRepo questionRepo,
                             UserRepo userRepo,
                             ModelMapper modelMapper) {
        this.answerRepo = answerRepo;
        this.questionParticipantRepo = questionParticipantRepo;
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public Answer create(Answer answer) {
        return answerRepo.save(answer);
    }

    @Override
    public AnswerDto updateAnswer(Long id, AnswerDto answerDto) {
        Answer answer = answerRepo.findById(id).orElseThrow(()->
                new AnswerNotFoundException(ExceptionMessage.ANSWER_NOT_FOUND));
        Question question = questionRepo.findByAnswerId(id);
        questionParticipantRepo.deleteAllByUserAndQuestion(getAuthenticatedUser(userRepo), question);
        answer.setAnswerText(answerDto.getAnswerText());
        answerRepo.save(answer);
        return modelMapper.map(answer, AnswerDto.class);
    }

    @Override
    @Transactional
    public void deleteAnswer(Long id) {
        Question question = questionRepo.findByAnswerId(id);
        questionParticipantRepo.deleteAllByUserAndQuestion(getAuthenticatedUser(userRepo), question);
        try {
            answerRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new AnswerNotFoundException(ExceptionMessage.ANSWER_NOT_FOUND);
        }
    }
}
