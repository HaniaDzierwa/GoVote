package com.aleklew.ballot.modules.ballot.models;

import lombok.Value;

@Value
public class CreateQuestionRequest {
    int ballotId;
    int orderNumber;
    String questionContent;
    String questionType;
    String[] answers;
}
