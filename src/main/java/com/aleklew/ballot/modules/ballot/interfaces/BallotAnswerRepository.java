package com.aleklew.ballot.modules.ballot.interfaces;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BallotAnswerRepository extends JpaRepository<BallotAnswerEntity, Integer> {
}
