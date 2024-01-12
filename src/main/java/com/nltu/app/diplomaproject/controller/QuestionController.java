package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.dto.PollResultsDto;
import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.service.AnswerService;
import com.nltu.app.diplomaproject.service.QuestionService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final ModelMapper modelMapper;

    public QuestionController(QuestionService questionService, AnswerService answerService, ModelMapper modelMapper) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<Page<QuestionDto>> getAllQuestions(Pageable pageable){
        return ResponseEntity.ok(questionService.getAllQuestions(pageable));
    }

    @PostMapping
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionDto questionDto,
                                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        Question question = modelMapper.map(questionDto, Question.class);
        return ResponseEntity.ok(questionService.create(question));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable Long id){
        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@Valid @PathVariable Long id,
                                                      @RequestBody QuestionDto questionDto,
                                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResponseEntity.ok(questionService.updateQuestion(id, questionDto));
    }

    @PostMapping("/{id}/vote")
    public ResponseEntity<String> voteQuestion(@PathVariable Long id, @RequestParam List<Long> answerIds){
        return ResponseEntity.ok(questionService.voteQuestion(id, answerIds));
    }

    @PostMapping("/{id}/vote/cancel")
    public ResponseEntity<String> voteCancelQuestion(@PathVariable Long id){
        return ResponseEntity.ok(questionService.cancelVote(id));
    }

    @GetMapping("/results/{id}")
    public ResponseEntity<PollResultsDto> getResultsOfVoting(@PathVariable Long id){
        return ResponseEntity.ok(questionService.getResults(id));
    }

}
