package com.aleklew.ballot.modules.ballot.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class QuestionWithAnswerRestModel {
    int questionId;
    String answer;
}
