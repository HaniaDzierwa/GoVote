package com.aleklew.ballot.modules.general.interfaces;

import com.aleklew.ballot.modules.general.db.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.general.rest.dto.CreateBallotRequest;

import java.util.List;

public interface BallotService {
    boolean deleteBallot(int ballotId);
    BallotEntity createBallot(CreateBallotRequest request);
    BallotEntity getBallotById(int ballotId);

    //todo update ballot ( only name and description)
}
