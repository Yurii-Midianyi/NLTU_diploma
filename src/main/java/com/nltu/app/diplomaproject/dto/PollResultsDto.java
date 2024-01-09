package com.nltu.app.diplomaproject.dto;

import java.util.List;

public class PollResultsDto {
    private Long countOfParticipants;
    private List<AnswerResultDto> answerResults;

    public PollResultsDto(){}

    public PollResultsDto(Long countOfParticipants, List<AnswerResultDto> answerResults) {
        this.countOfParticipants = countOfParticipants;
        this.answerResults = answerResults;
    }

    public Long getCountOfParticipants() {
        return countOfParticipants;
    }

    public void setCountOfParticipants(Long countOfParticipants) {
        this.countOfParticipants = countOfParticipants;
    }

    public List<AnswerResultDto> getAnswerResults() {
        return answerResults;
    }

    public void setAnswerResults(List<AnswerResultDto> answerResults) {
        this.answerResults = answerResults;
    }
}
