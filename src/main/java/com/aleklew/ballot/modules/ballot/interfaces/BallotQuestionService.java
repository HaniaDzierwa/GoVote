package com.aleklew.ballot.modules.ballot.interfaces;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotQuestionEntity;
import com.aleklew.ballot.modules.ballot.models.CreateQuestionRequest;

import java.util.Set;

public interface BallotQuestionService {

    boolean deleteBallotQuestion(int ballotId);
    BallotQuestionEntity createBallotQuestion(CreateQuestionRequest request);
    BallotQuestionEntity getBallotQuestionById(int questionId);

    Set<BallotQuestionEntity> getQuestionsByBallotId(int ballotId);

    //todo update ballot question ( only questionContent, questionType, answers )
}
