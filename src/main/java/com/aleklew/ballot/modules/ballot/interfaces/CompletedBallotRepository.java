package com.aleklew.ballot.modules.ballot.interfaces;

import com.aleklew.ballot.modules.ballot.dbmodels.CompletedBallotEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompletedBallotRepository extends JpaRepository<CompletedBallotEntity, Integer> {

    List<CompletedBallotEntity> findByBallotIdAndUserId(int ballotId, int userId);

    List<CompletedBallotEntity> findByUserId(int userId);
}
