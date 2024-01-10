package com.nltu.app.diplomaproject.service.imp;

import com.nltu.app.diplomaproject.dto.AnswerResultDto;
import com.nltu.app.diplomaproject.dto.PollResultsDto;
import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Answer;
import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.entity.QuestionParticipant;
import com.nltu.app.diplomaproject.entity.User;
import com.nltu.app.diplomaproject.exceptions.AnswerNotFoundException;
import com.nltu.app.diplomaproject.exceptions.ExceptionMessage;
import com.nltu.app.diplomaproject.exceptions.NonExistingAnswerForQuestionException;
import com.nltu.app.diplomaproject.exceptions.QuestionNotFoundException;
import com.nltu.app.diplomaproject.exceptions.VotingPeriodEndedException;
import com.nltu.app.diplomaproject.repository.AnswerRepo;
import com.nltu.app.diplomaproject.repository.QuestionParticipantRepo;
import com.nltu.app.diplomaproject.repository.QuestionRepo;
import com.nltu.app.diplomaproject.repository.UserRepo;
import com.nltu.app.diplomaproject.service.QuestionService;
import com.nltu.app.diplomaproject.service.ResultMessages;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;
    private final UserRepo userRepo;
    private final AnswerRepo answerRepo;
    private final ModelMapper modelMapper;
    private final QuestionParticipantRepo questionParticipantRepo;

    public QuestionServiceImpl(QuestionRepo questionRepo, UserRepo userRepo, AnswerRepo answerRepo, ModelMapper modelMapper, QuestionParticipantRepo questionParticipantRepo) {
        this.questionRepo = questionRepo;
        this.userRepo = userRepo;
        this.answerRepo = answerRepo;
        this.modelMapper = modelMapper;
        this.questionParticipantRepo = questionParticipantRepo;
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepo.findAll();
        return questions.stream().map(a->modelMapper.map(a, QuestionDto.class)).toList();
    }

    @Override
    public QuestionDto create(Question question) {
        User user = getAuthenticatedUser();
        question.setOrganizer(user);

        List<Answer> answers = question.getAnswers();
        answers.forEach(a->a.setQuestion(question));
        Question result = questionRepo.save(question);
        return modelMapper.map(result, QuestionDto.class);
    }

    @Override
    public QuestionDto getQuestion(Long id) {
        Question question = questionRepo.findById(id).orElseThrow(()->
                new QuestionNotFoundException(ExceptionMessage.QUESTION_NOT_FOUND));
        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public void deleteQuestion(Long id) {
        try {
            questionRepo.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new QuestionNotFoundException(ExceptionMessage.QUESTION_NOT_FOUND);
        }
    }

    @Transactional
    @Override
    public QuestionDto updateQuestion(Long id, QuestionDto questionDto) {
        Question question = questionRepo.findById(id).orElseThrow(() ->
                new RuntimeException(ExceptionMessage.QUESTION_NOT_FOUND));

        answerRepo.deleteAllByQuestionId(id);

        question.setQuestionText(questionDto.getQuestionText());
        question.setAnswers(questionDto.getAnswers().stream()
                .map(a->{
                    Answer answer = modelMapper.map(a, Answer.class);
                    answer.setQuestion(question);
                    return answer;})
                .collect(Collectors.toList()));
        question.setIsAnonymous(questionDto.getIsAnonymous());
        questionRepo.save(question);
        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    @Transactional
    public String voteQuestion(Long questionId, List<Long> answerIds) {
        User user = getAuthenticatedUser();
        Question question = questionRepo.findById(questionId).orElseThrow(()->
                new QuestionNotFoundException(ExceptionMessage.QUESTION_NOT_FOUND));

        LocalDateTime endDateTime = question.getEndDateTime();
        LocalDateTime currentDateTime = LocalDateTime.now();

        if (currentDateTime.isAfter(endDateTime)) {
            throw new VotingPeriodEndedException(ExceptionMessage.VOTING_PERIOD_ENDED);
        }

        if(questionParticipantRepo.existsByUserAndQuestion(user, question)){
            questionParticipantRepo.deleteAllByUserAndQuestion(user, question);
        }

        List<Answer> answers = new ArrayList<>();
        answerIds.forEach(a->{
            Answer answer = answerRepo.findById(a).orElseThrow(()->
                    new AnswerNotFoundException(ExceptionMessage.ANSWER_NOT_FOUND));
            if(!question.getAnswers().contains(answer)){
                throw new NonExistingAnswerForQuestionException(ExceptionMessage.WRONG_ANSWER_ID);
            }
            answers.add(answer);
        });
        for(Answer answer: answers){
            QuestionParticipant questionParticipant = new QuestionParticipant();
            questionParticipant.setUser(user);
            questionParticipant.setQuestion(question);
            questionParticipant.setAnswer(answer);
            
            questionParticipantRepo.save(questionParticipant);
        }
        return ResultMessages.VOTE_SAVED;
    }

    @Override
    public PollResultsDto getResults(Long id) {
        questionRepo.findById(id).orElseThrow(()->
                new QuestionNotFoundException(ExceptionMessage.QUESTION_NOT_FOUND));
        PollResultsDto pollResultsDto = new PollResultsDto();
        pollResultsDto.setCountOfParticipants(questionParticipantRepo.countDistinctUserByQuestionId(id));
        List<AnswerResultDto> answerResults = questionParticipantRepo.countAnswerResults(id);
        List<Answer> initializedAnswers = answerRepo.findAllByQuestionId(id);
        Map<String, AnswerResultDto> answerResultMap = new HashMap<>();

        // Initialize the map with existing answerResults
        for (AnswerResultDto result : answerResults) {
            answerResultMap.put(result.getAnswerText(), result);
        }

        // Add new answers with count 0 to the map
        for (Answer a : initializedAnswers) {
            answerResultMap.putIfAbsent(a.getAnswerText(), new AnswerResultDto(a.getAnswerText(), 0L));
        }

        pollResultsDto.setAnswerResults(new ArrayList<>(answerResultMap.values()));
        return pollResultsDto;
    }

    @Override
    @Transactional
    public String cancelVote(Long questionId) {
        User user = getAuthenticatedUser();
        Question question = questionRepo.findById(questionId).orElseThrow(()->
                new QuestionNotFoundException(ExceptionMessage.QUESTION_NOT_FOUND));
        questionParticipantRepo.deleteAllByUserAndQuestion(user, question);
        return ResultMessages.VOTE_CANCELED;
    }

    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepo.findByEmail(currentPrincipalName).orElseThrow(()->
                new UsernameNotFoundException(ExceptionMessage.USER_NOT_FOUND));
    }
}
