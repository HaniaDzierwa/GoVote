package com.aleklew.ballot.modules.ballot.interfaces;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.ballot.models.CompletedBallotRequest;
import com.aleklew.ballot.modules.ballot.models.CreateBallotRequest;
import com.aleklew.ballot.modules.ballot.models.QuestionWithAnswerResponse;

import java.util.List;

public interface BallotService {
    boolean deleteBallot(int ballotId);

    BallotEntity createBallot(CreateBallotRequest request);

    BallotEntity getBallotById(int ballotId);

    boolean publishBallotById(int ballotId);

    boolean completeBallot(CompletedBallotRequest completedBallotRequest, int userId);

    List<QuestionWithAnswerResponse> getQuestionWithAnswerByBallotId(int ballotId, int userId);

    List<BallotEntity> getCompletedBallots(int userId);

    List<BallotEntity> getNotCompletedBallots(int userId);
    //todo update ballot ( only name and description)
}
