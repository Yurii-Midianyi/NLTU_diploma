package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.constants.HttpStatuses;
import com.nltu.app.diplomaproject.dto.PollResultsDto;
import com.nltu.app.diplomaproject.dto.QuestionDto;
import com.nltu.app.diplomaproject.entity.Question;
import com.nltu.app.diplomaproject.service.AnswerService;
import com.nltu.app.diplomaproject.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final ModelMapper modelMapper;

    public QuestionController(QuestionService questionService, ModelMapper modelMapper) {
        this.questionService = questionService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get all questions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
    })
    @GetMapping
    public ResponseEntity<Page<QuestionDto>> getAllQuestions(@ParameterObject Pageable pageable){
        return ResponseEntity.ok(questionService.getAllQuestions(pageable));
    }

    @Operation(summary = "Create question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
    })
    @PostMapping
    public ResponseEntity<?> createQuestion(@Valid @RequestBody QuestionDto questionDto,
                                                      BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        Question question = modelMapper.map(questionDto, Question.class);
        return ResponseEntity.ok(questionService.create(question));
    }

    @Operation(summary = "Get single question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestion(@Parameter(description = "Id of question")
                                                   @PathVariable Long id){
        return ResponseEntity.ok(questionService.getQuestion(id));
    }

    @Operation(summary = "Delete question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@Parameter(description = "Id of question") @PathVariable Long id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@Parameter(description = "Id of question")
                                            @Valid @PathVariable Long id,
                                            @RequestBody QuestionDto questionDto,
                                            BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
        return ResponseEntity.ok(questionService.updateQuestion(id, questionDto));
    }

    @Operation(summary = "Vote for answer in question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    @PostMapping("/{id}/vote")
    public ResponseEntity<String> voteQuestion(@Parameter(description = "Id of question") @PathVariable Long id,
                                               @RequestParam(value = "Id of answer") @Valid @NotEmpty @NotNull List<Long> answerIds){
        return ResponseEntity.ok(questionService.chooseVoting(id, answerIds));
    }

    @Operation(summary = "Cancel vote")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    @PostMapping("/{id}/vote/cancel")
    public ResponseEntity<String> voteCancelQuestion(@PathVariable Long id){
        return ResponseEntity.ok(questionService.cancelVote(id));
    }

    @Operation(summary = "Get polling result")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "404", description = HttpStatuses.NOT_FOUND)
    })
    @GetMapping("/results/{id}")
    public ResponseEntity<PollResultsDto> getResultsOfVoting(@PathVariable Long id){
        return ResponseEntity.ok(questionService.getResults(id));
    }

}
