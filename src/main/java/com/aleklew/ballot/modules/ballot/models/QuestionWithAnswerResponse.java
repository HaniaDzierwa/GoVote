package com.aleklew.ballot.modules.ballot.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class QuestionWithAnswerResponse {
    String question;
    String answer;
}
