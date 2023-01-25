package com.aleklew.ballot.modules.ballot.models;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CompletedBallotResponse {
    List<QuestionWithAnswerResponse> questionWithAnswerRestModels;
}
