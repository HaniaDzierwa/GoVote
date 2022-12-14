package com.aleklew.ballot.modules.general.db.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aleklew.ballot.modules.general.db.dbmodels.BallotQuestion;

@Repository
public interface BallotQuestionRepository extends JpaRepository<BallotQuestion, Integer> {
}
