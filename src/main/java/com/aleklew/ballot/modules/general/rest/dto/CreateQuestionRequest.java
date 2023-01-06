package com.aleklew.ballot.modules.general.rest.dto;

import lombok.Value;
import java.util.Set;

@Value
public class CreateQuestionRequest {
    int ballotId;
    int orderNumber;
    String questionContent;
    String questionType;
    Set<String> answers;
}
