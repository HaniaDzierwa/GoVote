package com.aleklew.ballot.modules.ballot.services;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.ballot.dbmodels.BallotQuestionEntity;
import com.aleklew.ballot.modules.ballot.dbmodels.CompletedBallotEntity;
import com.aleklew.ballot.modules.ballot.interfaces.*;
import com.aleklew.ballot.modules.ballot.models.CompletedBallotRequest;
import com.aleklew.ballot.modules.ballot.models.CreateBallotRequest;
import com.aleklew.ballot.modules.ballot.models.QuestionWithAnswerResponse;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BaseBallotService implements BallotService {
    @Autowired
    private BallotRepository ballotRepository;

    @Autowired
    private CompletedBallotRepository completedBallotRepository;

    @Autowired
    private BallotQuestionRepository ballotQuestionRepository;

    @Autowired
    private BallotAnswerRepository ballotAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public BallotEntity createBallot(CreateBallotRequest request) {
        BallotEntity ballot = new BallotEntity(request.getOwnerId(),
                request.getBallotName(),
                request.getBallotDescription(),
                request.isPublished());
        return ballotRepository.save(ballot);
    }

    @Override
    public BallotEntity getBallotById(int ballotId) {
        Optional<BallotEntity> ballot = ballotRepository.findById(ballotId);
        return ballot.orElse(null);
    }

    @Override
    public boolean publishBallotById(int ballotId) {
        try {
            BallotEntity ballot = getBallotById(ballotId);
            ballot.setPublished(true);
            ballotRepository.save(ballot);
        } catch (Exception e) {
            return false;
        }
        return true;
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

    @Override
    public boolean completeBallot(CompletedBallotRequest completedBallotRequest, int userId) {
        try {
            var completedBallotEntity = completedBallotRequest
                    .getQuestionWithAnswer()
                    .stream()
                    .map(p -> new CompletedBallotEntity().builder()
                            .userId(userId)
                            .ballotId(completedBallotRequest.getBallotId())
                            .questionId(p.getQuestionId())
                            .answer(p.getAnswer())
                            .build()
                    )
                    .collect(Collectors.toList());

            completedBallotRepository.saveAll(completedBallotEntity);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public List<QuestionWithAnswerResponse> getQuestionWithAnswerByBallotId(int ballotId, int userId) {
        var completedBallotEntities = completedBallotRepository.findByBallotIdAndUserId(ballotId, userId);
        var questionContents = completedBallotEntities
                .stream()
                .map(e -> {
                            var question = ballotQuestionRepository
                                    .findById(e.getQuestionId())
                                    .map(BallotQuestionEntity::getQuestionContent)
                                    .orElse("");
                            return new QuestionWithAnswerResponse(question, e.getAnswer());
                        }
                )
                .collect(Collectors.toList());

        return questionContents;
    }

    @Override
    public List<BallotEntity> getCompletedBallots(int userId) {
        var ballotsIds = completedBallotRepository
                .findByUserId(userId)
                .stream()
                .map(CompletedBallotEntity::getBallotId)
                .collect(Collectors.toList());

        return ballotRepository.findByBallotIdIn(ballotsIds);
    }


    @Override
    public List<BallotEntity> getNotCompletedBallots(int userId) {
        var ballotsIds = completedBallotRepository
                .findByUserId(userId)
                .stream()
                .map(CompletedBallotEntity::getBallotId)
                .collect(Collectors.toList());

        return ballotRepository.findByBallotIdNotIn(ballotsIds)
                .stream()
                .filter(BallotEntity::isPublished)
                .collect(Collectors.toList());
    }
}
