package com.aleklew.ballot.modules.ballot.interfaces;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BallotQuestionRepository extends JpaRepository<BallotQuestionEntity, Integer> {
    Set<BallotQuestionEntity> findByBallotId(int balllotId);
}
