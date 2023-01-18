package com.aleklew.ballot.modules.ballot.services;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotAnswerEntity;
import com.aleklew.ballot.modules.ballot.dbmodels.BallotQuestionEntity;
import com.aleklew.ballot.modules.ballot.interfaces.BallotQuestionRepository;
import com.aleklew.ballot.modules.ballot.interfaces.BallotQuestionService;
import com.aleklew.ballot.modules.ballot.models.CreateQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class BaseBallotQuestionCreatorService implements BallotQuestionService {

    @Autowired
    private BallotQuestionRepository ballotQuestionRepository;

    @Override
    public BallotQuestionEntity createBallotQuestion(CreateQuestionRequest request) {
        BallotQuestionEntity ballotQuestionEntity =
                BallotQuestionEntity.builder()
                        .ballotId(request.getBallotId())
                        .orderNumber(request.getOrderNumber())
                        .questionContent(request.getQuestionContent())
                        .type(request.getQuestionType())
                        .answers(null)
                        .build();

        var answers = Arrays.stream(request.getAnswers()).collect(Collectors.toSet());
        AtomicInteger answerCounter = new AtomicInteger(answers.size());
        ballotQuestionEntity.setAnswers(
                answers.stream()
                        .map(
                                answer ->
                                        new BallotAnswerEntity()
                                                .withQuestionId(ballotQuestionEntity)
                                                //todo token implementation
                                                .withResponderToken(0)
                                                .withAnswerText(answer)
                                                .withAnswerOrderNumber(answerCounter.decrementAndGet())


                        )
                        .collect(Collectors.toSet())
        );

        return ballotQuestionRepository.save(ballotQuestionEntity);
    }

    @Override
    public BallotQuestionEntity getBallotQuestionById(int questionId) {
        Optional<BallotQuestionEntity> question = ballotQuestionRepository.findById(questionId);
        return question.orElse(null);
    }

    @Override
    public boolean deleteBallotQuestion(int questionId) {
        Optional<BallotQuestionEntity> questionToRemove = ballotQuestionRepository.findById(questionId);
        if (questionToRemove.isPresent()) {
            ballotQuestionRepository.deleteById(questionToRemove.get().getQuestionId());
            return true;
        }
        return false;
    }

    @Override
    public Set<BallotQuestionEntity> getQuestionsByBallotId(int ballotId) {
        final var selectedQuestions =
                ballotQuestionRepository.findByBallotId(ballotId);
        if (!selectedQuestions.isEmpty()) {
            return selectedQuestions;
        }
        return null;
    }
}


