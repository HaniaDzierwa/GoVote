package com.aleklew.ballot.modules.general.services;

import com.aleklew.ballot.modules.general.db.dbmodels.Ballot;
import com.aleklew.ballot.modules.general.db.dbmodels.BallotQuestion;
import com.aleklew.ballot.modules.general.db.interfaces.BallotAnswerRepository;
import com.aleklew.ballot.modules.general.db.interfaces.BallotQuestionRepository;
import com.aleklew.ballot.modules.general.db.interfaces.BallotRepository;
import com.aleklew.ballot.modules.general.interfaces.IBallotCreatorService;
import com.aleklew.ballot.modules.general.models.test.CreateBallotRequest;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;
import com.aleklew.ballot.modules.profiles.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BaseBallotCreatorService implements IBallotCreatorService {

    @Autowired
    private BallotRepository ballotRepository;

    @Autowired
    private BallotQuestionRepository ballotQuestionRepository;

    @Autowired
    private BallotAnswerRepository ballotAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void empty() {

    }

    @Override
    public Ballot createBallot(CreateBallotRequest request) {
        Ballot ballot = new Ballot(request.getOwnerID(), request.getBallotName(), request.getBallotDescription());

        Optional<User> user = userRepository.findById(request.getOwnerID());
        if (user.isPresent()) {
            ballot = ballotRepository.save(ballot);
            return ballot;
        }

        return null;
    }

    @Override
    public boolean deleteBallot(int ballotId) {
        Optional<Ballot> ballotToRemove = ballotRepository.findById(ballotId);
        if (ballotToRemove.isPresent()) {
            ballotRepository.deleteById(ballotToRemove.get().getBallotID());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        Optional<BallotQuestion> questionToRemove = ballotQuestionRepository.findById(questionId);
        if (questionToRemove.isPresent()) {
            ballotQuestionRepository.deleteById(questionToRemove.get().getQuestionID());
            return true;
        }
        return false;
    }

}
