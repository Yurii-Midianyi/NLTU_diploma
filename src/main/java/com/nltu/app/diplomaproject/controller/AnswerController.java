package com.nltu.app.diplomaproject.controller;

import com.nltu.app.diplomaproject.constants.HttpStatuses;
import com.nltu.app.diplomaproject.dto.AnswerDto;
import com.nltu.app.diplomaproject.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Update answer by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN),
    })
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAnswer(
            @Parameter(description = "Id of answer which will be updated")
            @Valid @PathVariable Long id,
            @RequestBody AnswerDto answerDto){

        return ResponseEntity.ok(answerService.updateAnswer(id, answerDto));
    }

    @Operation(summary = "Delete answer by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = HttpStatuses.OK),
            @ApiResponse(responseCode = "400", description = HttpStatuses.BAD_REQUEST),
            @ApiResponse(responseCode = "401", description = HttpStatuses.UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = HttpStatuses.FORBIDDEN)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(
            @Parameter(description = "Id of answer which will be deleted") @PathVariable Long id){

        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
