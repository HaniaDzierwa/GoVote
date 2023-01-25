package com.aleklew.ballot.modules.ballot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CompletedBallotRequest {
    int ballotId;
    List<QuestionWithAnswerRestModel> questionWithAnswer;
}
