package com.aleklew.ballot.modules.general.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleklew.ballot.modules.general.dbmodels.BallotQuestion;

@Repository
public interface BallotQuestionRepository extends JpaRepository<BallotQuestion, Integer> {
}
