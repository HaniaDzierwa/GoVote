package com.aleklew.ballot.modules.ballot.models;

import lombok.Value;

@Value
public class CreateAnswerRequest {
    int question;
    int responderToken;
    String answerText;
    int answerOrderNumber;
}
