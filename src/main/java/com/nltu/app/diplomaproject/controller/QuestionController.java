package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Answer;
import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.service.AnswerService;
import com.nltu.app.diplomaproject.service.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    public ResponseEntity<List<Question>> getAllQuestions(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @PostMapping
    public ResponseEntity<QuestionDto> createQuestion(@RequestBody QuestionDto questionDto){
        Question question = modelMapper.map(questionDto, Question.class);
        return ResponseEntity.ok(questionService.create(question));
    }
}