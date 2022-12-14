package com.aleklew.ballot.modules.general.interfaces;

import com.aleklew.ballot.modules.general.db.dbmodels.Ballot;
import com.aleklew.ballot.modules.general.models.test.CreateBallotRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface IBallotCreatorService {
    void empty();

    boolean deleteBallot(int ballotId);
    boolean deleteQuestion(int questionId);

    Ballot createBallot(CreateBallotRequest request);
    Ballot getBallotByID(int ballotId);

}
