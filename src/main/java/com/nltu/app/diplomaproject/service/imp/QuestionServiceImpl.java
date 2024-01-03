package com.nltu.app.diplomaproject.service.imp;

import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Answer;
import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.entity.User;
import com.nltu.app.diplomaproject.repository.AnswerRepo;
import com.nltu.app.diplomaproject.repository.QuestionRepo;
import com.nltu.app.diplomaproject.repository.UserRepo;
import com.nltu.app.diplomaproject.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;
    private final UserRepo userRepo;
    private final AnswerRepo answerRepo;
    private final ModelMapper modelMapper;

    public QuestionServiceImpl(QuestionRepo questionRepo, UserRepo userRepo, AnswerRepo answerRepo, ModelMapper modelMapper) {
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.answerRepo = answerRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepo.findAll();
        return questions.stream().map(a->modelMapper.map(a, QuestionDto.class)).toList();
    }

    @Override
    public QuestionDto create(Question question) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userRepo.findByEmail(currentPrincipalName).orElseThrow(()->new UsernameNotFoundException(""));
        question.setOrganizer(user);

        List<Answer> answers = question.getAnswers();
        answers.forEach(a->a.setQuestion(question));
        Question result = questionRepo.save(question);
        return modelMapper.map(result, QuestionDto.class);
    }

    @Override
    public QuestionDto getQuestion(Long id) {
        Question question = questionRepo.findById(id).orElseThrow(()->new RuntimeException("Question not found"));
        return modelMapper.map(question, QuestionDto.class);
    }
}
