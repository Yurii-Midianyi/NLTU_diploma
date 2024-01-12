package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.dto.AnswerDto;
import com.nltu.app.diplomaproject.service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAnswer(@Valid @PathVariable Long id,
                                            @RequestBody AnswerDto answerDto){
        return ResponseEntity.ok(answerService.updateAnswer(id, answerDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id){
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
