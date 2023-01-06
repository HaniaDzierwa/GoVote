package com.aleklew.ballot.modules.general.rest.dto;

import lombok.Value;

@Value
public class CreateAnswerRequest {
    int question;
    int responderToken;
    String answerText;
    int answerOrderNumber;
}
