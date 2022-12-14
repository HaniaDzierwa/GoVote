package com.aleklew.ballot.modules.general.interfaces;

import com.aleklew.ballot.modules.general.db.dbmodels.Ballot;
import com.aleklew.ballot.modules.general.models.test.CreateBallotRequest;

public interface IBallotCreatorService {
    void empty();

    boolean deleteBallot(int ballotId);
    boolean deleteQuestion(int questionId);

    Ballot createBallot(CreateBallotRequest request);

}
