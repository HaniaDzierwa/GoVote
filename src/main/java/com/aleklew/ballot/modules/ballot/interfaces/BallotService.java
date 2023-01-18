package com.aleklew.ballot.modules.ballot.interfaces;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.ballot.models.CreateBallotRequest;

public interface BallotService {
    boolean deleteBallot(int ballotId);
    BallotEntity createBallot(CreateBallotRequest request);
    BallotEntity getBallotById(int ballotId);

    //todo update ballot ( only name and description)
}
