package com.aleklew.ballot.modules.general.services;

import com.aleklew.ballot.modules.general.db.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.general.db.interfaces.BallotAnswerRepository;
import com.aleklew.ballot.modules.general.db.interfaces.BallotQuestionRepository;
import com.aleklew.ballot.modules.general.db.interfaces.BallotRepository;
import com.aleklew.ballot.modules.general.interfaces.BallotService;
import com.aleklew.ballot.modules.general.rest.dto.CreateBallotRequest;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.util.Optional;

@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BaseBallotService implements BallotService {
    @Autowired
    private BallotRepository ballotRepository;

    @Autowired
    private BallotQuestionRepository ballotQuestionRepository;

    @Autowired
    private BallotAnswerRepository ballotAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BallotEntity createBallot(CreateBallotRequest request) {
        BallotEntity ballot = new BallotEntity(request.getOwnerId(), request.getBallotName(), request.getBallotDescription());
        return ballotRepository.save(ballot);
    }

    @Override
    public BallotEntity getBallotById(int ballotId) {
        Optional<BallotEntity> ballot = ballotRepository.findById(ballotId);
        return ballot.orElse(null);
    }

    @Override
    public boolean deleteBallot(int ballotId) {
        try {
            ballotRepository.deleteById(ballotId);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
