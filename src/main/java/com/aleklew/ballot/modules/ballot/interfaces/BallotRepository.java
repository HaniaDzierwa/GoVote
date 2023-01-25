package com.aleklew.ballot.modules.ballot.interfaces;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BallotRepository extends JpaRepository<BallotEntity, Integer> {
    List<BallotEntity> findByBallotIdIn(List<Integer> ballotIdList);

    List<BallotEntity> findByBallotIdNotIn(List<Integer> ballotIdList);
}
